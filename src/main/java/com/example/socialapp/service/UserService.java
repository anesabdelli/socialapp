package com.example.socialapp.service;
//sse = server sent event

import com.example.socialapp.model.User;
import com.example.socialapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username) {
        UUID uuid = UUID.randomUUID();
        return userRepository.saveUser(new User(uuid, username));
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findUserById(id);

    }
}