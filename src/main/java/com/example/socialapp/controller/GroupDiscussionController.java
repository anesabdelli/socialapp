package com.example.socialapp.controller;

import com.example.socialapp.model.GroupDiscussion;
import com.example.socialapp.service.GroupDiscussionService;
import com.example.socialapp.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group-discussions")
public class GroupDiscussionController {

    private final GroupDiscussionService groupDiscussionService;
    private final MessageService messageService;

    public GroupDiscussionController(GroupDiscussionService groupDiscussionService, MessageService messageService) {
        this.groupDiscussionService = groupDiscussionService;
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<GroupDiscussion> createGroup(@RequestBody CreateGroupBody body) {
        GroupDiscussion group = groupDiscussionService.createGroup(body.name(), body.memberIds());
        return ResponseEntity.ok(group);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<GroupDiscussion>> getGroups(@PathVariable String userId) {
        return ResponseEntity.ok(groupDiscussionService.getGroupsByUser(userId));
    }

    @PostMapping("/{groupId}/messages")
    public ResponseEntity<Void> sendMessage(@PathVariable String groupId, @RequestBody SendGroupMessageBody body) {
        messageService.sendGroupMessage(groupId, body.senderId(), body.content());
        return ResponseEntity.status(201).build();
    }

    record CreateGroupBody(String name, List<String> memberIds) {}
    record SendGroupMessageBody(String senderId, String content) {}
}
