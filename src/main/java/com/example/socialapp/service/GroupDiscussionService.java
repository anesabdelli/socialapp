package com.example.socialapp.service;

import com.example.socialapp.model.GroupDiscussion;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.GroupDiscussionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GroupDiscussionService {

    private final GroupDiscussionRepository groupDiscussionRepository;
    private final UserService userService;

    public GroupDiscussionService(GroupDiscussionRepository groupDiscussionRepository, UserService userService) {
        this.groupDiscussionRepository = groupDiscussionRepository;
        this.userService = userService;
    }

    public GroupDiscussion createGroup(String name, List<String> memberIds) {
        List<User> members = memberIds.stream()
                .map(id -> userService.getUserById(id)
                        .orElseThrow(() -> new RuntimeException("User not found: " + id)))
                .toList();

        GroupDiscussion group = new GroupDiscussion(UUID.randomUUID().toString(), name, members, LocalDateTime.now());
        return groupDiscussionRepository.save(group);
    }

    public List<GroupDiscussion> getGroupsByUser(String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return groupDiscussionRepository.findByMember(user);
    }

    public Optional<GroupDiscussion> findById(String id) {
        return groupDiscussionRepository.findById(id);
    }
}
