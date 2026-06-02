package com.example.socialapp.repository;

import com.example.socialapp.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryUserRepository implements UserRepository {
    HashMap<UUID, UserModel> users = new HashMap<>();

    @Override
    public Optional<UserModel> findUserById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public UserModel saveUser(UserModel user) {
        users.put(user.getId(), user);
        return user;
    }
}