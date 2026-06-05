package com.example.socialapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_discussions")
public class GroupDiscussion {
    @Id
    private String id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "group_discussion_members",
        joinColumns = @JoinColumn(name = "group_discussion_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;

    private LocalDateTime createdAt;
}
