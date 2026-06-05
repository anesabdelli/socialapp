package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.GroupDiscussion;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.GroupDiscussionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("sql")
public class SQLGroupDiscussionRepository implements GroupDiscussionRepository {

    private final SpringDataGroupDiscussionRepository springRepo;

    public SQLGroupDiscussionRepository(SpringDataGroupDiscussionRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public GroupDiscussion save(GroupDiscussion groupDiscussion) {
        return springRepo.save(groupDiscussion);
    }

    @Override
    public Optional<GroupDiscussion> findById(String id) {
        return springRepo.findById(id);
    }

    @Override
    public List<GroupDiscussion> findByMember(User user) {
        return springRepo.findByMembersContaining(user);
    }
}
