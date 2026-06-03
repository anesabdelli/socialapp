package com.example.socialapp.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.socialapp.model.Message;
import com.example.socialapp.repository.MessageRepository;
import java.util.Optional;

@Component
public class InMemoryMessageRepository implements MessageRepository {
    private final List<Message> messages = new ArrayList<>();

    @Override
    public void save(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> findByDiscussionId(String discussionId) {
        return messages.stream()
            .filter(message -> message.getDiscussionId().equals(discussionId))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Message> findById(String id) {
        return messages.stream()
            .filter(m -> m.getId().equals(id))
            .findFirst();
    }

    @Override
    public void deleteById(String id) {
        messages.removeIf(m -> m.getId().equals(id));
    }

}