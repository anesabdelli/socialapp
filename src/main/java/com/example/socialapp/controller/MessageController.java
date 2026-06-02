package com.example.socialapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.dto.SendMessageRequest;
import com.example.socialapp.service.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    
}
