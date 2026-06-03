package com.example.socialapp.controller;

import com.example.socialapp.dto.CreateFriendRequestDto;
import com.example.socialapp.model.FriendRequest;
import com.example.socialapp.service.FriendRequestService;
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
    public FriendRequest createFriendRequest(@RequestBody CreateFriendRequestDto createFriendRequestDto) {
        return friendRequestService.createFriendRequest(createFriendRequestDto.getReceiverId(), createFriendRequestDto.getSenderId());
    }

    @GetMapping("/{receiverId}")
    public List<FriendRequest> findByReceiverId(@PathVariable String receiverId) {
        return friendRequestService.findByReceiverId(receiverId);
    }
}