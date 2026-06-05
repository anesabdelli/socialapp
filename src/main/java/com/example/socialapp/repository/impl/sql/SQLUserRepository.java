package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.User;
import com.example.socialapp.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("sql")
public class SQLUserRepository implements UserRepository {

    private final SpringDataUserRepository springRepo;

    public SQLUserRepository(SpringDataUserRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Optional<User> findUserById(String id) {
        return springRepo.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return springRepo.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return springRepo.save(user);
    }
}
