package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataDiscussionRepository extends JpaRepository<Discussion, String> {
    List<Discussion> findByUser1OrUser2(User user1, User user2);
    Optional<Discussion> findByUser1AndUser2(User user1, User user2);
}
