package com.example.socialapp.service;

import com.example.socialapp.model.FriendRequest;
import com.example.socialapp.model.FriendRequestStatus;
import com.example.socialapp.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;

    public FriendRequestService(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    public List<FriendRequest> findByReceiverId(String receiverId) {
        return friendRequestRepository.findByReceiverId(receiverId);
    }

    public FriendRequest createFriendRequest(String senderId, String receivedId) {
        String uuid = UUID.randomUUID().toString();
        FriendRequest friendRequest = new FriendRequest(uuid, receivedId, senderId, FriendRequestStatus.PENDING);
        return friendRequestRepository.saveRequest(friendRequest);
    }

    public FriendRequest declineFriendRequest(String id) {
        return friendRequestRepository.updateStatusRequest(id, FriendRequestStatus.DECLINED);
    }

    public void cancelFriendRequest(String id) {
        friendRequestRepository.deleteFriendRequest(id);
    }
}