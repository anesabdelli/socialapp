package com.example.socialapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter

public class User{
    private UUID id;
    private String username;

    public User(UUID id, String username) {
        this.id = id;
        this.username = username;
    }
}