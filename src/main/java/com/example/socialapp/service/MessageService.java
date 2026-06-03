package com.example.socialapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.Message;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final DiscussionService discussionService;
    private final UserService userService;

    public MessageService(MessageRepository messageRepository, DiscussionService discussionService, UserService userService){
        this.messageRepository = messageRepository;
        this.discussionService = discussionService;
        this.userService = userService;
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
    }

    public List<Message> getMessagesByDiscussion(String discussionId){
        return messageRepository.findByDiscussionId(discussionId);
    }

    public void editMessage(String id, String newContent) {
        Optional<Message> existing = messageRepository.findById(id);
        if(existing.isPresent()){
            existing.get().setContent(newContent);
        }
    }

    public void deleteMessage(String id){
        messageRepository.deleteById(id);
    }
}
