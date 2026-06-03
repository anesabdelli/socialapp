package com.example.socialapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FriendRequest {
    private String id;
    private String senderId;
    private String receiverId;
    private FriendRequestStatus status;

    public FriendRequest(String id, String senderId, String receiverId, FriendRequestStatus status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }
}