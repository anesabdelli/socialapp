package com.example.socialapp.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.repository.DiscussionRepository;

@Component
public class InMemoryDiscussionRepository implements DiscussionRepository {
    private final List<Discussion> discussions = new ArrayList<>();

    @Override
    public void save(Discussion discussion) {
        discussions.add(discussion);
    }

    @Override
    public List<Discussion> findByUserId(String userId) {
        return discussions.stream()
            .filter(d -> d.getUser1Id().equals(userId) || d.getUser2Id().equals(userId))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Discussion> findByUser1IdAndUser2Id(String user1Id, String user2Id) {
        return discussions.stream()
            .filter(d -> d.getUser1Id().equals(user1Id) && d.getUser2Id().equals(user2Id))
            .findFirst();
    }
}
