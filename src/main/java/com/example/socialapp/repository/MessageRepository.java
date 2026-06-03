package com.example.socialapp.repository;

import java.util.List;
import java.util.Optional;

import com.example.socialapp.model.Message;

public interface MessageRepository {
    void save(Message message);
    List<Message> findByDiscussionId(String discussionId); 
    Optional<Message> findById(String id);
    void deleteById(String id);
}
