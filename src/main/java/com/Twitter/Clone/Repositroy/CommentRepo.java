package com.Twitter.Clone.Repositroy;

import com.Twitter.Clone.Model.Comment;
import com.Twitter.Clone.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {
    Collection<Comment> findByTwitterPost(Post validPost);

}
