package com.example.socialapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.dto.SendMessageRequest;
import com.example.socialapp.model.Message;
import com.example.socialapp.service.MessageService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
    
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("")
    public ResponseEntity<Void> sendMessage(@RequestBody SendMessageRequest request) {
        messageService.sendMessage(
            request.getContent(),
            request.getSenderId(), 
            request.getDiscussionId()
        );

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String discussionId) {
        List<Message> messages = messageService.getMessagesByDiscussion(discussionId);
        return ResponseEntity.ok(messages);
    }
    
    
}
