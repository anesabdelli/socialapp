package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataFriendRequestRepository extends JpaRepository<FriendRequest, String> {
    List<FriendRequest> findByReceiverId(String receiverId);
}
