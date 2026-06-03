package com.example.socialapp.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.DiscussionRepository;

@Component
public class InMemoryDiscussionRepository implements DiscussionRepository {
    private final List<Discussion> discussions = new ArrayList<>();

    @Override
    public void save(Discussion discussion) {
        discussions.add(discussion);
    }

    @Override
    public List<Discussion> findByUser(User user) {
        return discussions.stream()
            .filter(d -> d.getUser1().getId().equals(user.getId()) || d.getUser2().getId().equals(user.getId()))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Discussion> findByUsers(User user1, User user2) {
        return discussions.stream()
            .filter(d -> d.getUser1().getId().equals(user1.getId()) && d.getUser2().getId().equals(user2.getId()))
            .findFirst();
    }
}
