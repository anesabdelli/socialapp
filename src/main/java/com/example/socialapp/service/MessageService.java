package com.example.socialapp.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.UUID;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.Message;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final DiscussionService discussionService;
    private final UserService userService;
    private final NotificationService notificationService;

    public MessageService(MessageRepository messageRepository, DiscussionService discussionService, UserService userService, NotificationService notificationService){
        this.messageRepository = messageRepository;
        this.discussionService = discussionService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void sendMessage(String content, String senderId, String receiverId){
        User sender = userService.getUserById(senderId).orElseThrow(() -> new RuntimeException("User not found: " + senderId));

        Discussion discussion = discussionService.getOrCreateDiscussion(senderId, receiverId);

        String id = UUID.randomUUID().toString();
        LocalDateTime sentAt = LocalDateTime.now();
        Message message = new Message(id, content, sender, discussion.getId(), sentAt);
        messageRepository.save(message);
        log.info("Message sent | id={} | sender={} | discussion={} | sentAt={}",
            message.getId(),
            message.getSender().getId(),
            message.getDiscussionId(),
            message.getSentAt()
        );
        notificationService.notify(receiverId, "NEW_MESSAGE", message.getId(), discussion.getId());
    }

    public List<Message> getMessagesByDiscussion(String discussionId){
        return messageRepository.findByDiscussionId(discussionId);
    }

    public void editMessage(String id, String newContent) {
        Optional<Message> existing = messageRepository.findById(id);
        if (existing.isPresent()) {
            Message message = existing.get();
            message.setContent(newContent);

            discussionService.findById(message.getDiscussionId()).ifPresent(discussion -> {
                new HashSet<>(List.of(discussion.getUser1().getId(), discussion.getUser2().getId()))
                    .forEach(userId -> notificationService.notify(userId, "MESSAGE_EDITED", id, discussion.getId()));
            });
        }
    }

    public void deleteMessage(String id) {
        messageRepository.findById(id).ifPresent(message -> {
            messageRepository.deleteById(id);

            // collection sans doublons
            discussionService.findById(message.getDiscussionId()).ifPresent(discussion -> {
                new HashSet<>(List.of(discussion.getUser1().getId(), discussion.getUser2().getId()))
                    .forEach(userId -> notificationService.notify(userId, "MESSAGE_DELETED", id, discussion.getId()));
            });
        });
    }
    public Message sendFile(String discussionId, MultipartFile file) throws IOException, IOException {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setDiscussionId(discussionId);
        message.setFileName(file.getOriginalFilename());
        message.setFileData(file.getBytes());
        message.setType("FILE");
        message.setSentAt(LocalDateTime.now());
        messageRepository.save(message);
        return message;
    }
}
