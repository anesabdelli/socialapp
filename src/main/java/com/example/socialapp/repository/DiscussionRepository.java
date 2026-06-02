package com.example.socialapp.repository;

import java.util.List;
import java.util.Optional;

import com.example.socialapp.model.Discussion;

public interface DiscussionRepository {
    void save(Discussion discussion);
    List<Discussion> findByUserId(String userId);
    Optional<Discussion> findByUser1IdAndUser2Id(String user1Id, String user2Id);
}
