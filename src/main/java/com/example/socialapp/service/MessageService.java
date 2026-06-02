package com.example.socialapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.Message;
import com.example.socialapp.repository.MessageRepository;

public class MessageService {
    private final MessageRepository messageRepository;
    private final DiscussionService discussionService;

    public MessageService(MessageRepository messageRepository, DiscussionService discussionService){
        this.messageRepository = messageRepository;
        this.discussionService = discussionService;
    }

    public void sendMessage(String content, String senderId, String receiverId){

        Discussion discussion = discussionService.getOrCreateDiscussion(senderId, receiverId);

        String id = UUID.randomUUID().toString();
        LocalDateTime sentAt = LocalDateTime.now();
        Message message = new Message(id, content, senderId, discussion.getId(), sentAt);
        messageRepository.save(message);
    }

    public List<Message> getMessagesByDiscussion(String discussionId){
        return messageRepository.findByDiscussionId(discussionId);
    }
}
