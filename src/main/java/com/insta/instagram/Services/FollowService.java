package com.insta.instagram.Services;

import com.insta.instagram.Model.Follow;
import com.insta.instagram.Model.User;
import com.insta.instagram.Repositroy.FollowRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    FollowRepo followRepo;
    public boolean isFollowAllowed(User followTargetUser, User follower) {
        List<Follow> followList = followRepo.findByCurrentUserAndUserFollower(followTargetUser,follower);

        return followList != null && followList.isEmpty() && !followTargetUser.equals(follower);
    }

    public void startFollowing(Follow follow, User follower) {
        follow.setUserFollower(follower);
        followRepo.save(follow);
    }

    public Follow findFollow(Integer followId) {
        return followRepo.findById(followId).orElse(null);
    }

    public void unfollow(Follow follow) {
        followRepo.delete(follow);
    }
}
