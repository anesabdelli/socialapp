package com.example.socialapp.repository.impl;

import com.example.socialapp.model.User;
import com.example.socialapp.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository {
    HashMap<String, User> users = new HashMap<>();

    @Override
    public Optional<User> findUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User saveUser(User user) {
        users.put((user.getId()), user);
        return user;
    }
}