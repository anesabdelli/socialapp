package com.example.socialapp.repository.impl.sql;

import com.example.socialapp.model.FriendRequest;
import com.example.socialapp.model.FriendRequestStatus;
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

    @Override
    public FriendRequest updateStatusRequest(String id, FriendRequestStatus status) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void deleteFriendRequest(String id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
