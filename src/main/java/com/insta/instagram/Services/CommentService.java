package com.insta.instagram.Services;

import com.insta.instagram.Model.Comment;
import com.insta.instagram.Repositroy.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;
    public String addComment(Comment comment) {
        comment.setFormattedTime(comment.getFormattedTime());
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
