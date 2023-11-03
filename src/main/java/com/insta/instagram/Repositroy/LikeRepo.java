package com.insta.instagram.Repositroy;

import com.insta.instagram.Model.Like;
import com.insta.instagram.Model.Post;
import com.insta.instagram.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface LikeRepo extends JpaRepository<Like,Integer> {
    List<Like> findByTwitterPostAndLiker(Post twitterPost, User liker);
    Collection<Like> findByTwitterPost(Post validPost);
}
