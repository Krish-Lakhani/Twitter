package com.insta.instagram.Services;

import com.insta.instagram.Model.Comment;
import com.insta.instagram.Model.Like;
import com.insta.instagram.Model.Post;
import com.insta.instagram.Model.User;
import com.insta.instagram.Repositroy.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

//    public boolean isCommentAllowedOnThisPost(Post twitterPost, User liker) {
//        List<Like> likeList = commentRepo.findByTwitterPostAndLiker(twitterPost,liker);
//        return likeList != null && likeList.isEmpty();
//    }

    public Integer getCommentCountForPost(Post validPost) {
        return commentRepo.findByTwitterPost(validPost).size();
    }

    public String addComment(Comment comment) {
        comment.setTime(comment.getTime());
        commentRepo.save(comment);
        return "Comment has been added!!!";
    }

    public Comment findComment(Integer commentId) {
        return  commentRepo.findById(commentId).orElse(null);
    }

    public void removeComment(Comment comment) {
        commentRepo.delete(comment);
    }

}
