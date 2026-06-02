package com.example.socialapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discussion {
    String id;
    String user1Id;
    String user2Id; 
    LocalDateTime createdAt;
}
