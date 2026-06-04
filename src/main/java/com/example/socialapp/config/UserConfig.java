package com.example.socialapp.config;

import com.example.socialapp.repository.UserRepository;
import com.example.socialapp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
