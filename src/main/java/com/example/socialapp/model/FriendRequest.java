package com.example.socialapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FriendRequest {
    @Id
    private String id;
    private String senderId;
    private String receiverId;
    @Enumerated(EnumType.STRING)
    private FriendRequestStatus status;

    public FriendRequest(String id, String senderId, String receiverId, FriendRequestStatus status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }
}