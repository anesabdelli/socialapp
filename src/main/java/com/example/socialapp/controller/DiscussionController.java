package com.example.socialapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialapp.model.Discussion;
import com.example.socialapp.service.DiscussionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/discussions")
public class DiscussionController {
    private DiscussionService discussionService;

    public DiscussionController(DiscussionService discussionService){
        this.discussionService = discussionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Discussion>> getDiscussions(@PathVariable String userId) {
        List<Discussion> discussions = discussionService.getDiscussionsByUser(userId);
        return ResponseEntity.ok(discussions);
    }
    
}
