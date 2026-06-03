package com.example.socialapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter

public class User{
    private String id;
    private String username;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }
}