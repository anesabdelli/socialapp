package com.example.socialapp.repository;

import com.example.socialapp.model.UserModel;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<UserModel> findUserById(UUID id);

    UserModel saveUser(UserModel user);
}