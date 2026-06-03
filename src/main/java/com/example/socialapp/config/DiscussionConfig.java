package com.example.socialapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.socialapp.repository.DiscussionRepository;
import com.example.socialapp.service.DiscussionService;
import com.example.socialapp.service.UserService;

@Configuration
public class DiscussionConfig {
    @Bean
    public DiscussionService discussionService(DiscussionRepository discussionRepository, UserService userService){
        return new DiscussionService(discussionRepository, userService);
    }
}
