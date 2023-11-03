package com.insta.instagram.Services;

import com.insta.instagram.Model.Like;
import com.insta.instagram.Model.Post;
import com.insta.instagram.Model.User;
import com.insta.instagram.Model.dto.Credential;
import com.insta.instagram.Model.dto.PostDto;
import com.insta.instagram.Repositroy.UserRepo;
import com.insta.instagram.Services.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    public String SignUp(User user) throws NoSuchAlgorithmException {

        if (userRepo.existsByuserEmail(user.getUserEmail())){
            return "Already Register";
        }
        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(user.getUserPassword());
        user.setUserPassword(hashPass);
        userRepo.save(user);
        return "Register Successfully";

    }

    public String SignIn(Credential credential) throws NoSuchAlgorithmException {
        if (!userRepo.existsByuserEmail(credential.getEmail())){
            return "Please Create a Account";
        }
        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(credential.getPassword());
        User user = userRepo.findByUserEmail(credential.getEmail());

        if (hashPass.equals(user.getUserPassword())) {
            user.setStatus("login");
            userRepo.save(user);
            return "login";
        } else {
            return "Credential MisMatch";
        }
    }

    public String SignOut(String email) {
        User user = userRepo.findByUserEmail(email);
        if(user.getStatus().equals("login")){
            user.setStatus("logout");
            userRepo.save(user);
        }else {
            return "Please signIn first";
        }
        return "User Signed out successfully";
    }

    public String CreatePost(Post post, String email) {
        User user = userRepo.findByUserEmail(email);
        if(user.getStatus().equals("login")) {
            User postOwner = userRepo.findByUserEmail(email);
            post.setPostOwner(postOwner);
            postOwner.setTotal(postOwner.getTotal() + 1);
            postService.CreatePost(post);
        }else {
            return "Please signIn first";
        }
        return "Post Upload Successfully";
    }

//    public List<PostDto> showPost(String email) {
//        User user = userRepo.findByUserEmail(email);
//        user.setUserName(user.getUserName());
//        return postService.showPost(user);
//    }

    public List<Post> ShowPost(String email) {
        User postOwner = userRepo.findByUserEmail(email);
        return postService.ShowPost(email);
    }

    public String deletePost(Integer postId, String email) {
        User user = userRepo.findByUserEmail(email);
        if(user.getStatus().equals("login") && user.getTotal() > 0){
            user.setTotal(user.getTotal() - 1);
            postService.deletePost(postId,user);
        }else {
            return "Please signIn first";
        }
        return "Post Deleted Successfully";
    }


    public String addLike(Like like, String likeEmail) {
        Post twitterPost = like.getTwitterPost();
        boolean postValid = postService.validatePost(twitterPost);

        if (postValid) {
            User liker = userRepo.findByUserEmail(likeEmail);
            if (likeService.isLikeAllowedOnThisPost(twitterPost, liker)) {
                like.setLiker(liker);
                return likeService.addLike(like);
            } else {
                return "Already Liked!!";
            }
        } else {
            return "Cannot like on Invalid Post!!";
        }
    }
}
