package com.example.socialapp.repository;

import com.example.socialapp.model.User;

import java.util.Optional;


public interface UserRepository {

    Optional<User> findUserById(String id);
    Optional<User> findUserByUsername(String username);
    User saveUser(User user);
}