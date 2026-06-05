package com.example.socialapp.repository.impl.inmemory;

import com.example.socialapp.model.GroupDiscussion;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.GroupDiscussionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Profile("inmemory")
public class InMemoryGroupDiscussionRepository implements GroupDiscussionRepository {
    private final List<GroupDiscussion> groups = new ArrayList<>();

    @Override
    public GroupDiscussion save(GroupDiscussion groupDiscussion) {
        groups.add(groupDiscussion);
        return groupDiscussion;
    }

    @Override
    public Optional<GroupDiscussion> findById(String id) {
        return groups.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<GroupDiscussion> findByMember(User user) {
        return groups.stream()
                .filter(g -> g.getMembers().stream().anyMatch(m -> m.getId().equals(user.getId())))
                .toList();
    }
}
