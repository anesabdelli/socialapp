package com.example.socialapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.StringToClassMapItem;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.socialapp.dto.EditMessageRequest;
import com.example.socialapp.dto.SendMessageRequest;
import com.example.socialapp.model.Message;
import com.example.socialapp.service.MessageService;

import java.util.List;

import org.springframework.http.HttpHeaders;
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

    @PostMapping(value = "/conversations/{id}/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a file")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "multipart/form-data",
                    schema = @Schema(type = "object", properties = {
                            @StringToClassMapItem(key = "file", value = byte[].class)
                    })))
    public ResponseEntity<?> uploadFile(
            @PathVariable String id,
            @RequestParam String senderId,
            @RequestPart("file") MultipartFile file
    ) {
        Message message = messageService.sendFile(id, senderId, file);
        return ResponseEntity.status(201).body(message);
    }

    @GetMapping("/files/{id}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        return messageService.findById(id)
                .filter(m -> "FILE".equals(m.getType()))
                .map(m -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + m.getFileName() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .body(m.getFileData()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable String id) {
        messageService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
