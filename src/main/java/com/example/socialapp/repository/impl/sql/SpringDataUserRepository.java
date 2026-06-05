package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
