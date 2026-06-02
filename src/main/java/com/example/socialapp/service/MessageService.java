package com.example.socialapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.socialapp.model.Message;
import com.example.socialapp.repository.MessageRepository;

public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public void sendMessage(String content, String senderId, String discussionId){
        String id = UUID.randomUUID().toString();
        LocalDateTime sentAt = LocalDateTime.now();
        Message message = new Message(id, content, senderId, discussionId, sentAt);
        messageRepository.save(message);
    }

    public List<Message> getMessagesByDiscussion(String discussionId){
        return messageRepository.findByDiscussionId(discussionId);
    }
}
