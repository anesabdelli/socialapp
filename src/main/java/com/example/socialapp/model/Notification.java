package com.example.socialapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private String id;
    private String userId;
    private String type;
    private String messageId;
    private String discussionId;
    private LocalDateTime createdAt;
}
