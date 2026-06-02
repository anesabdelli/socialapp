package com.example.socialapp.repository;

import com.example.socialapp.model.UserModel;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUserRepository implements UserRepository {
    HashMap<UUID, UserModel> users = new HashMap<>();

    @Override
    public Optional<UserModel> findUserByID(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public UserModel saveUser(UserModel user) {
        users.put(user.getId(), user);
        return user;
    }
}