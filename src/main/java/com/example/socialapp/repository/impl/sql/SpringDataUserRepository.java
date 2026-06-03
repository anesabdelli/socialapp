package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<User, String> {
}
