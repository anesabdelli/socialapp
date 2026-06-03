package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.DiscussionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("sql")
public class SQLDiscussionRepository implements DiscussionRepository {

    private final SpringDataDiscussionRepository springRepo;

    public SQLDiscussionRepository(SpringDataDiscussionRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public void save(Discussion discussion) {
        springRepo.save(discussion);
    }

    @Override
    public List<Discussion> findByUser(User user) {
        return springRepo.findByUser1OrUser2(user, user);
    }

    @Override
    public Optional<Discussion> findByUsers(User user1, User user2) {
        return springRepo.findByUser1AndUser2(user1, user2);
    }

    @Override
    public Optional<Discussion> findById(String id) {
        return springRepo.findById(id);
    }
}
