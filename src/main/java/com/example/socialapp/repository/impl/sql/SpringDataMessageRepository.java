package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataMessageRepository extends JpaRepository<Message, String> {
    List<Message> findByDiscussionId(String discussionId);
}
