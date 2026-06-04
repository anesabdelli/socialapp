package com.example.socialapp.repository;

import com.example.socialapp.model.FriendRequest;
import com.example.socialapp.model.FriendRequestStatus;

import java.util.List;

public interface FriendRequestRepository {

    List<FriendRequest> findByReceiverId(String receiverId);
    FriendRequest saveRequest(FriendRequest friendRequest);
    void deleteFriendRequest(String id);
    FriendRequest updateStatusRequest(String id, FriendRequestStatus status);
}
