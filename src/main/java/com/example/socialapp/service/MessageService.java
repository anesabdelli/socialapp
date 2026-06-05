package com.example.socialapp.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.UUID;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.GroupDiscussion;
import com.example.socialapp.model.Message;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final DiscussionService discussionService;
    private final GroupDiscussionService groupDiscussionService;
    private final UserService userService;
    private final NotificationService notificationService;

    public MessageService(MessageRepository messageRepository, DiscussionService discussionService, GroupDiscussionService groupDiscussionService, UserService userService, NotificationService notificationService) {
        this.messageRepository = messageRepository;
        this.discussionService = discussionService;
        this.groupDiscussionService = groupDiscussionService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void sendMessage(String content, String senderId, String receiverId) {
        User sender = userService.getUserById(senderId).orElseThrow(() -> new RuntimeException("User not found: " + senderId));
        Discussion discussion = discussionService.getOrCreateDiscussion(senderId, receiverId);
        String id = UUID.randomUUID().toString();
        LocalDateTime sentAt = LocalDateTime.now();
        Message message = new Message(id, content, sender, "TEXT", (byte[]) null, null, discussion.getId(), sentAt);
        messageRepository.save(message);
        log.info("Message sent | id={} | sender={} | discussion={} | sentAt={}", message.getId(), message.getSender().getId(), message.getDiscussionId(), message.getSentAt());
        notificationService.notify(receiverId, "NEW_MESSAGE", message.getId(), discussion.getId());
    }

    public void sendGroupMessage(String groupDiscussionId, String senderId, String content) {
        User sender = userService.getUserById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found: " + senderId));
        GroupDiscussion group = groupDiscussionService.findById(groupDiscussionId)
                .orElseThrow(() -> new RuntimeException("Group discussion not found: " + groupDiscussionId));
        boolean isMember = group.getMembers().stream().anyMatch(m -> m.getId().equals(senderId));
        if (!isMember) throw new RuntimeException("User is not a member of this group");
        Message message = new Message(UUID.randomUUID().toString(), content, sender, "TEXT", null, null, groupDiscussionId, LocalDateTime.now());
        messageRepository.save(message);
        log.info("Group message sent | id={} | sender={} | group={} | sentAt={}", message.getId(), senderId, groupDiscussionId, message.getSentAt());
        group.getMembers().stream()
                .filter(m -> !m.getId().equals(senderId))
                .forEach(m -> notificationService.notify(m.getId(), "NEW_MESSAGE", message.getId(), groupDiscussionId));
    }

    public List<Message> getMessagesByDiscussion(String discussionId) {
        return messageRepository.findByDiscussionId(discussionId);
    }

    public Optional<Message> findById(String id) {
        return messageRepository.findById(id);
    }

    public void editMessage(String id, String newContent) {
        messageRepository.findById(id).ifPresent(message -> {
            message.setContent(newContent);
            messageRepository.save(message);
            notifyMembers(message.getDiscussionId(), "MESSAGE_EDITED", id);
        });
    }

    public void deleteMessage(String id) {
        messageRepository.findById(id).ifPresent(message -> {
            messageRepository.deleteById(id);
            notifyMembers(message.getDiscussionId(), "MESSAGE_DELETED", id);
        });
    }

    public Message sendFile(String discussionId, String senderId, MultipartFile file) {
        User sender = userService.getUserById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found: " + senderId));
        try {
            String id = UUID.randomUUID().toString();
            byte[] fileData = file.getBytes();
            String fileName = file.getOriginalFilename();
            Message message = new Message(id, null, sender, "FILE", fileData, fileName, discussionId, LocalDateTime.now());
            messageRepository.save(message);
            log.info("File sent | id={} | sender={} | discussion={} | file={} | sentAt={}", id, senderId, discussionId, fileName, message.getSentAt());
            notifyMembers(discussionId, "NEW_FILE", id);
            return message;
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }
    }

    public void deleteFile(String id) {
        messageRepository.findById(id).ifPresent(message -> {
            if (!"FILE".equals(message.getType())) {
                throw new RuntimeException("Ce message n'est pas un fichier");
            }
            messageRepository.deleteById(id);
            notifyMembers(message.getDiscussionId(), "FILE_DELETED", id);
        });
    }

    private void notifyMembers(String discussionId, String type, String messageId) {
        discussionService.findById(discussionId).ifPresentOrElse(
            discussion -> new HashSet<>(List.of(discussion.getUser1().getId(), discussion.getUser2().getId()))
                    .forEach(userId -> notificationService.notify(userId, type, messageId, discussionId)),
            () -> groupDiscussionService.findById(discussionId).ifPresent(group ->
                    group.getMembers().forEach(m -> notificationService.notify(m.getId(), type, messageId, discussionId)))
        );
    }
}
