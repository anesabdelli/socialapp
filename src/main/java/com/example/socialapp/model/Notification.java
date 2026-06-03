package com.example.socialapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    private String id;
    private String userId;
    private String type;
    private String messageId;
    private String discussionId;
    private LocalDateTime createdAt;
}
