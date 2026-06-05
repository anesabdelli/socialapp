package com.example.socialapp.service;
//sse = server sent event

import com.example.socialapp.model.User;
import com.example.socialapp.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username) {
        String uuid = UUID.randomUUID().toString();
        return userRepository.saveUser(new User(uuid, username));
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findUserById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}