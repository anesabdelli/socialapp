package com.example.socialapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.socialapp.repository.DiscussionRepository;
import com.example.socialapp.service.DiscussionService;

@Configuration
public class DiscussionConfig {
    @Bean
    public DiscussionService discussionService(DiscussionRepository discussionRepository){
        return new DiscussionService(discussionRepository);
    }

}
