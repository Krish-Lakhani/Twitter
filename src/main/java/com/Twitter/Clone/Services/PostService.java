package com.Twitter.Clone.Services;

import com.Twitter.Clone.Model.User;
import com.Twitter.Clone.Model.Post;
import com.Twitter.Clone.Repositroy.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    public Post CreatePost(Post post) {
        return postRepo.save(post);
    }


    public String deletePost(Integer postId, User user) {
        Post post = postRepo.findById(postId).orElse(null);
        if (post != null && post.getPostOwner().equals(user)) {
            postRepo.deleteById(postId);
            return "Removed successfully";
        }
        return "Post does not exist";
    }

    public boolean validatePost(Post twitterPost) {
        return (twitterPost != null && postRepo.existsById(twitterPost.getPostId()));
    }
    public Post getPostById(Integer postId) {
        return postRepo.findById(postId).orElse(null);
    }


}
