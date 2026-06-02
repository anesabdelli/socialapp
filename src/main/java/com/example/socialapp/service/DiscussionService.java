package com.example.socialapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.repository.DiscussionRepository;

public class DiscussionService {
    private final DiscussionRepository discussionRepository;

    public DiscussionService(DiscussionRepository discussionRepository){
        this.discussionRepository = discussionRepository;
    }

    public Discussion getOrCreateDiscussion(String user1Id, String user2Id) {
        Optional<Discussion> existing = discussionRepository.findByUser1IdAndUser2Id(user1Id, user2Id);

        if (existing.isPresent()) {
            return existing.get();
        }

        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Discussion discussion = new Discussion(id, user1Id, user2Id, createdAt);
        discussionRepository.save(discussion);
        return discussion;
    }

    public List<Discussion> getDiscussionsByUser(String userId){
        return discussionRepository.findByUserId(userId);
    }
}
