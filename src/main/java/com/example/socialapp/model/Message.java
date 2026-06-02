package com.example.socialapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    String id;
    String content;
    String senderId; // TODO: change to type User later
    String discussionId;
    LocalDateTime sentAt;
}
