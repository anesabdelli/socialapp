package com.example.socialapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter

public class UserModel {
    private UUID id;
    private String username;

    public UserModel(UUID id, String username) {
        this.id = UUID.randomUUID();
        this.username = username;
    }
}