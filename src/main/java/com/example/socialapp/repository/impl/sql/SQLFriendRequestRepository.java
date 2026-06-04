package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.FriendRequest;
import com.example.socialapp.repository.FriendRequestRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("sql")
public class SQLFriendRequestRepository implements FriendRequestRepository {

    private final SpringDataFriendRequestRepository springRepo;

    public SQLFriendRequestRepository(SpringDataFriendRequestRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public List<FriendRequest> findByReceiverId(String receiverId) {
        return springRepo.findByReceiverId(receiverId);
    }

    @Override
    public FriendRequest saveRequest(FriendRequest friendRequest) {
        return springRepo.save(friendRequest);
    }
}
