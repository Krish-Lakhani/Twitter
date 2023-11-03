package com.insta.instagram.Controller;

import com.insta.instagram.Model.*;
import com.insta.instagram.Model.dto.Credential;
import com.insta.instagram.Model.dto.PostDto;
import com.insta.instagram.Services.PostService;
import com.insta.instagram.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("User")
//@Tag(name = "Greeting", description = "Endpoints for greeting messages")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/SignUp")
    private String SignUp(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.SignUp(user);
    }

    @GetMapping("/SignIn")
    private String SignIn(@RequestBody Credential credential) throws NoSuchAlgorithmException {
        return userService.SignIn(credential);
    }

    @GetMapping("SignOut")
    private String SignOut(@RequestParam String email) throws NoSuchAlgorithmException {
        return userService.SignOut(email);
    }

    @PostMapping("Post")
    private String CreatePost(@RequestBody Post post, @RequestParam String email) {
        return userService.CreatePost(post, email);
    }

    @GetMapping("/ShowPost/{email}")
    public List<Post> showPost(@PathVariable String email) {
        return userService.ShowPost(email);
    }

    //    @GetMapping("/showPost/{email}")
//    private List<PostDto> showPost(@PathVariable String email){
//        return userService.showPost(email);
//    }
    @DeleteMapping("deletePost")
    public String deletePost(@RequestParam Integer postId, @RequestParam String email) {
        return userService.deletePost(postId, email);
    }

    @PostMapping("like")
    private String addLike(@RequestBody Like like, @RequestParam String likeEmail) {
        return userService.addLike(like, likeEmail);
    }

    @GetMapping("totalLike/{postId}")
    public String totalLike(@PathVariable Integer postId) {
        return userService.totalLike(postId);
    }

    @DeleteMapping("DeleteLike")
    public String deleteLike(@RequestParam Integer likeId, @RequestParam String email) {
        return userService.deleteLike(likeId, email);
    }

    @PostMapping("follow")
    public String FollowUser(@RequestBody Follow follow, @RequestParam String followerEmail) {
        return userService.FollowUser(follow, followerEmail);
    }

    @DeleteMapping("unfollow/{followId}")
    public String unFollowUser(@PathVariable Integer followId, @RequestParam String followerEmail) {
        return userService.unFollowUser(followId, followerEmail);
    }

    @PostMapping("comment")
    public String addComment(@RequestBody Comment comment, @RequestParam String commenterEmail) {
        return userService.addComment(comment, commenterEmail);
    }
    @DeleteMapping("comment")
    public String removeComment(@RequestParam Integer commentId, @RequestParam String email) {
        return userService.removeComment(commentId, email);
    }
}
