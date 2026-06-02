package com.example.socialapp.repository;

import java.util.List;

import com.example.socialapp.model.Message;

public interface MessageRepository {
    void save(Message message);
    List<Message> findByDiscussionId(String discussionId); 
}
