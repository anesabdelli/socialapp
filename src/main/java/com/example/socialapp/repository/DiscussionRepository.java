package com.example.socialapp.repository;

import java.util.List;
import java.util.Optional;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.User;

public interface DiscussionRepository {
    void save(Discussion discussion);
    List<Discussion> findByUser(User user);
    Optional<Discussion> findByUsers(User user1, User user2);
    Optional<Discussion> findById(String id);
}
