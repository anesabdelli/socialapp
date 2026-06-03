package com.example.socialapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.DiscussionRepository;

public class DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final UserService userService;

    public DiscussionService(DiscussionRepository discussionRepository, UserService userService){
        this.discussionRepository = discussionRepository;
        this.userService = userService;
    }

    public Discussion getOrCreateDiscussion(String user1Id, String user2Id) {
        User user1 = userService.getUserById(user1Id).orElseThrow(() -> new RuntimeException("User not found: " + user1Id));
        User user2 = userService.getUserById(user2Id).orElseThrow(() -> new RuntimeException("User not found: " + user2Id));

        Optional<Discussion> existing = discussionRepository.findByUsers(user1, user2);

        if (existing.isPresent()) {
            return existing.get();
        }

        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Discussion discussion = new Discussion(id, user1, user2, createdAt);
        discussionRepository.save(discussion);
        return discussion;
    }

    public List<Discussion> getDiscussionsByUser(String userId){
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return discussionRepository.findByUser(user);
    }
}
