package com.example.socialapp.repository;

import com.example.socialapp.model.GroupDiscussion;
import com.example.socialapp.model.User;

import java.util.List;
import java.util.Optional;

public interface GroupDiscussionRepository {
    GroupDiscussion save(GroupDiscussion groupDiscussion);
    Optional<GroupDiscussion> findById(String id);
    List<GroupDiscussion> findByMember(User user);
}
