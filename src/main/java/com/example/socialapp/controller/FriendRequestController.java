package com.example.socialapp.controller;

import com.example.socialapp.dto.CreateFriendRequest;
import com.example.socialapp.model.FriendRequest;
import com.example.socialapp.service.FriendRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend-requests")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping
    public FriendRequest createFriendRequest(@RequestBody CreateFriendRequest createFriendRequest) {
        return friendRequestService.createFriendRequest(createFriendRequest.getReceiverId(), createFriendRequest.getSenderId());
    }

    @GetMapping("/{receiverId}")
    public ResponseEntity<List<FriendRequest>> findByReceiverId(@PathVariable String receiverId) {
        List<FriendRequest> requests = friendRequestService.findByReceiverId(receiverId);
        if (requests.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(requests); 
    }

    @PatchMapping("/{id}")
    public FriendRequest declineFriendRequets(@PathVariable String id) {
        return friendRequestService.declineFriendRequest(id);
    }

    @DeleteMapping("/{id}")
    public void cancelFriendRequest(@PathVariable String id) {
        friendRequestService.cancelFriendRequest(id);
    }
}