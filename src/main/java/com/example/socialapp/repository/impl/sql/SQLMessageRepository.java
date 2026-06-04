package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.Message;
import com.example.socialapp.repository.MessageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("sql")
public class SQLMessageRepository implements MessageRepository {

    private final SpringDataMessageRepository springRepo;

    public SQLMessageRepository(SpringDataMessageRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public void save(Message message) {
        springRepo.save(message);
    }

    @Override
    public List<Message> findByDiscussionId(String discussionId) {
        return springRepo.findByDiscussionId(discussionId);
    }

    @Override
    public Optional<Message> findById(String id) {
        return springRepo.findById(id);
    }

    @Override
    public void deleteById(String id) {
        springRepo.deleteById(id);
    }
}
