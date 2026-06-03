package com.example.socialapp.controller;

import org.springframework.web.bind.annotation.*;

import com.example.socialapp.dto.EditMessageRequest;
import com.example.socialapp.dto.SendMessageRequest;
import com.example.socialapp.model.Message;
import com.example.socialapp.service.MessageService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


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
            request.getReceiverId()
        );

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String discussionId) {
        List<Message> messages = messageService.getMessagesByDiscussion(discussionId);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editMessage(@PathVariable String id, @RequestBody EditMessageRequest request) {
        messageService.editMessage(id, request.getContent());
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    
}
