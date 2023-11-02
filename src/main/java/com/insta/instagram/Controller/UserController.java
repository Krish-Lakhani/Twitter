package com.insta.instagram.Controller;

import com.insta.instagram.Model.Like;
import com.insta.instagram.Model.Post;
import com.insta.instagram.Model.User;
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
    private String addLike(@RequestBody Like like,@RequestParam String likeEmail){
        return userService.addLike(like,likeEmail);
    }
}
