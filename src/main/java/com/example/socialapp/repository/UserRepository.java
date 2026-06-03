package com.example.socialapp.repository;

import com.example.socialapp.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUserById(String id);

    User saveUser(User user);
}