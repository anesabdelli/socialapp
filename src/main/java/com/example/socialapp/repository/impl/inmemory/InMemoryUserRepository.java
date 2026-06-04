package com.example.socialapp.repository.impl.inmemory;

import com.example.socialapp.model.User;
import com.example.socialapp.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
@Profile("inmemory")
public class InMemoryUserRepository implements UserRepository {
    private final HashMap<String, User> users = new HashMap<>();

    @Override
    public Optional<User> findUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User saveUser(User user) {
        users.put(user.getId(), user);
        return user;
    }
}
