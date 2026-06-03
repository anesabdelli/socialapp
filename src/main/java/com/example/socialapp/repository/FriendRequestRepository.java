package com.example.socialapp.repository;

import com.example.socialapp.model.FriendRequest;

import java.util.List;

public interface FriendRequestRepository {

    List<FriendRequest> findByReceiverId(String receiverId);
    FriendRequest saveRequest(FriendRequest friendRequest);
}
