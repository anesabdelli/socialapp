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
    private final NotificationService notificationService;
    private final DiscussionService discussionService;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, NotificationService notificationService, DiscussionService discussionService) {
        this.friendRequestRepository = friendRequestRepository;
        this.notificationService = notificationService;
        this.discussionService = discussionService;
    }

    public List<FriendRequest> findByReceiverId(String receiverId) {
        return friendRequestRepository.findByReceiverId(receiverId);
    }

    public FriendRequest createFriendRequest(String senderId, String receivedId) {
        String uuid = UUID.randomUUID().toString();
        FriendRequest friendRequest = new FriendRequest(uuid, receivedId, senderId, FriendRequestStatus.PENDING);
        notificationService.notify(receivedId, "FRIEND_REQUEST_RECEIVED", uuid);
        return friendRequestRepository.saveRequest(friendRequest);
    }

    public FriendRequest acceptFriendRequest(String id) {
        FriendRequest updated = friendRequestRepository.updateStatusRequest(id, FriendRequestStatus.ACCEPTED);
        discussionService.getOrCreateDiscussion(updated.getSenderId(), updated.getReceiverId());
        notificationService.notify(updated.getSenderId(), "FRIEND_REQUEST_ACCEPTED", id);
        return updated;
    }

    public FriendRequest declineFriendRequest(String id) {
        return friendRequestRepository.updateStatusRequest(id, FriendRequestStatus.DECLINED);
    }

    public void cancelFriendRequest(String id) {
        friendRequestRepository.deleteFriendRequest(id);
    }
}