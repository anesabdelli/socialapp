package com.example.socialapp.config;

import com.example.socialapp.repository.GroupDiscussionRepository;
import com.example.socialapp.service.GroupDiscussionService;
import com.example.socialapp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupDiscussionConfig {
    @Bean
    GroupDiscussionService groupDiscussionService(GroupDiscussionRepository groupDiscussionRepository, UserService userService) {
        return new GroupDiscussionService(groupDiscussionRepository, userService);
    }
}
