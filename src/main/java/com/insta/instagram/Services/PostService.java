package com.insta.instagram.Services;

import com.insta.instagram.Model.Post;
import com.insta.instagram.Model.User;
import com.insta.instagram.Model.dto.PostDto;
import com.insta.instagram.Repositroy.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    public Post CreatePost(Post post) {
        return postRepo.save(post);
    }

//    public List<PostDto> showPost(User user) {
//        return postRepo.getPostsByPostOwner(user);
//    }

    public List<Post> ShowPost(String userEmail) {
        return postRepo.findByPostOwnerUserEmail(userEmail);
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
}
