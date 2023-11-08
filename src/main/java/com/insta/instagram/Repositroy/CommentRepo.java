package com.insta.instagram.Repositroy;

import com.insta.instagram.Model.Comment;
import com.insta.instagram.Model.Like;
import com.insta.instagram.Model.Post;
import com.insta.instagram.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {
    Collection<Comment> findByTwitterPost(Post validPost);

//
//    List<Like> findByTwitterPostAndLiker(Post twitterPost, User liker);
}
