package com.Twitter.Clone.Services;

import com.Twitter.Clone.Model.Follow;
import com.Twitter.Clone.Model.User;
import com.Twitter.Clone.Repositroy.FollowRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    FollowRepo followRepo;

    @PersistenceContext
    private EntityManager entityManager;

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

    public int getTotalFollow(User user) {
        return entityManager.createQuery(
                        "SELECT COUNT(f) FROM Follow f WHERE f.currentUser = :user", Long.class)
                .setParameter("user", user)
                .getSingleResult()
                .intValue();
    }
}
