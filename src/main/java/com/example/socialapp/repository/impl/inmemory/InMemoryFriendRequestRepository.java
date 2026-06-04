package com.example.socialapp.repository.impl.inmemory;

import com.example.socialapp.model.FriendRequest;
import com.example.socialapp.model.FriendRequestStatus;
import com.example.socialapp.repository.FriendRequestRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Profile("inmemory")
public class InMemoryFriendRequestRepository implements FriendRequestRepository {
    private final HashMap<String, FriendRequest> friendRequests = new HashMap<>();

    @Override
    public List<FriendRequest> findByReceiverId(String receiverId) {
        return friendRequests.values().stream()
                .filter(request -> request.getReceiverId().equals(receiverId))
                .toList();
    }

    @Override
    public FriendRequest saveRequest(FriendRequest friendRequest) {
        friendRequests.put(friendRequest.getId(), friendRequest);
        return friendRequest;
    }

    @Override
    public void deleteFriendRequest(String id) {
        friendRequests.remove(id);
    }

    @Override
    public FriendRequest updateStatusRequest(String id, FriendRequestStatus status) {
        FriendRequest request = friendRequests.get(id);
        request.setStatus(status);
        friendRequests.put(id, request);
        return request;
    }
}