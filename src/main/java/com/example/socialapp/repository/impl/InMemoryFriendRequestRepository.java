package com.example.socialapp.repository.impl;

import com.example.socialapp.model.FriendRequest;
import com.example.socialapp.repository.FriendRequestRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class InMemoryFriendRequestRepository implements FriendRequestRepository {
    HashMap<String, FriendRequest> friendRequests = new HashMap<>();

    @Override
    public List<FriendRequest> findByReceiverId(String receivedId) {
        return friendRequests.values().stream()
                .filter(request -> request.getReceiverId().equals(receivedId))
                .toList();
    }

    @Override
    public FriendRequest saveRequest(FriendRequest friendRequest) {
        friendRequests.put(friendRequest.getId(), friendRequest);
            return friendRequest;
    }
}