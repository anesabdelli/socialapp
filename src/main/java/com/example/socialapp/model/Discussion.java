package com.example.socialapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discussion {
    private String id;
    private User user1;
    private User user2;
    private LocalDateTime createdAt;
}
