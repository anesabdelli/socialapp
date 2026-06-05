package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.GroupDiscussion;
import com.example.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataGroupDiscussionRepository extends JpaRepository<GroupDiscussion, String> {
    List<GroupDiscussion> findByMembersContaining(User user);
}
