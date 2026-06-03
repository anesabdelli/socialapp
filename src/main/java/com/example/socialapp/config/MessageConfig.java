package com.example.socialapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.socialapp.repository.MessageRepository;
import com.example.socialapp.service.DiscussionService;
import com.example.socialapp.service.MessageService;
import com.example.socialapp.service.UserService;

@Configuration
public class MessageConfig {
    @Bean
    public MessageService messageService(MessageRepository messageRepository, DiscussionService discussionService, UserService userService){
        return new MessageService(messageRepository, discussionService, userService);
    }
}