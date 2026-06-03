package com.example.socialapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;
    private String content;
    private User sender;
    private String discussionId;
    private LocalDateTime sentAt;
}
