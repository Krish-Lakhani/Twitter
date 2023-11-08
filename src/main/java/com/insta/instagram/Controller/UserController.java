package com.insta.instagram.Controller;

import com.insta.instagram.Model.*;
import com.insta.instagram.Model.dto.Credential;
import com.insta.instagram.Model.dto.PostDto;
import com.insta.instagram.Services.FollowService;
import com.insta.instagram.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("User")
@Tag(name = "User Management", description = "Manage user accounts securely, including user registration, login, logout, password reset, and account verification. Simplify user-related operations with this API.")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    FollowService followService;

    @PostMapping("/SignUp")
    @Operation(
            summary = "User Registration",
            description = "Create a new user account securely. Allow users to register on the Twitter clone platform to access various features, including posting, liking, commenting, and following functionalities.",
            tags = {"User Management"}
    )
    private String SignUp(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.SignUp(user);
    }



    @GetMapping("/SignIn")
    @Operation(
            summary = "User Sign In",
            description = "Allow users to sign in securely to the Twitter clone platform.",
            tags = {"User Management"}
    )
    private String SignIn(@RequestBody Credential credential) throws NoSuchAlgorithmException {
        return userService.SignIn(credential);
    }




    @GetMapping("SignOut")
    @Operation(
            summary = "User Sign Out",
            description = "Allow users to sign out securely from the Twitter clone platform.",
            tags = {"User Management"}
    )
    private String SignOut(@RequestParam String email) throws NoSuchAlgorithmException {
        return userService.SignOut(email);
    }



    @PostMapping("Post")
    @Operation(
            summary = "Create a Post",
            description = "Allow users to create a new post on the Twitter clone platform.",
            tags = {"User Management"}
    )
    private String CreatePost(@RequestBody Post post, @RequestParam String email) {
        return userService.CreatePost(post, email);
    }


    @GetMapping("/showPost/{email}")
    @Operation(
            summary = "Show User Posts",
            description = "Retrieve and display all posts associated with the specified user's email.",
            tags = {"User Management"}
    )
    public List<PostDto> showPost(@PathVariable String email) {
        return userService.showPost(email);
    }


    @DeleteMapping("deletePost")
    @Operation(
            summary = "Delete a Post",
            description = "Delete a specific post using the post ID and associated user's email.",
            tags = {"User Management"}
    )
    public String deletePost(@RequestParam Integer postId, @RequestParam String email) {
        return userService.deletePost(postId, email);
    }

    @PostMapping("like")
    @Operation(
            summary = "Add Like",
            description = "Allow users to add a like to a post using the user's email.",
            tags = {"User Management"}
    )
    private String addLike(@RequestBody Like like, @RequestParam String likeEmail) {
        return userService.addLike(like, likeEmail);
    }



    @GetMapping("totalLike/{postId}")
    @Operation(
            summary = "Get Total Likes",
            description = "Retrieve the total number of likes for a specific post using the post ID.",
            tags = {"User Management"}
    )
    public String totalLike(@PathVariable Integer postId) {
        return userService.totalLike(postId);
    }



    @GetMapping("totalComment/{postId}")
    @Operation(
            summary = "Get Total Comments",
            description = "Retrieve the total number of comments for a specific post using the post ID.",
            tags = {"User Management"}
    )
    public String totalComment(@PathVariable Integer postId) {
        return userService.totalComment(postId);
    }




    @GetMapping("totalFollow/{userId}")
    @Operation(
            summary = "Get Total Followers",
            description = "Retrieve the total number of followers for a specific user using the user ID.",
            tags = {"User Management"}
    )
    public int getTotalFollow(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return followService.getTotalFollow(user);
    }



    @DeleteMapping("DeleteLike")
    @Operation(
            summary = "Delete Like",
            description = "Remove a specific like using the like ID and associated user's email.",
            tags = {"User Management"}
    )
    public String deleteLike(@RequestParam Integer likeId, @RequestParam String email) {
        return userService.deleteLike(likeId, email);
    }



    @PostMapping("follow")
    @Operation(
            summary = "Follow User",
            description = "Allow a user to follow another user on the Twitter clone platform.",
            tags = {"User Management"}
    )
    public String FollowUser(@RequestBody Follow follow, @RequestParam String followerEmail) {
        return userService.FollowUser(follow, followerEmail);
    }

    @DeleteMapping("unfollow/{followId}")
    @Operation(
            summary = "Unfollow User",
            description = "Allow a user to unfollow another user on the Twitter clone platform.",
            tags = {"User Management"}
    )
    public String unFollowUser(@PathVariable Integer followId, @RequestParam String followerEmail) {
        return userService.unFollowUser(followId, followerEmail);
    }

    @PostMapping("comment")
    @Operation(
            summary = "Add Comment",
            description = "Allow users to add comments to a post on the Twitter clone platform.",
            tags = {"User Management"}
    )
    public String addComment(@RequestBody Comment comment, @RequestParam String commenterEmail) {
        return userService.addComment(comment, commenterEmail);
    }


    @DeleteMapping("removeComment")
    @Operation(
            summary = "Remove Comment",
            description = "Allow users to remove their comments from a post on the Twitter clone platform.",
            tags = {"User Management"}
    )
    public String removeComment(@RequestParam Integer commentId, @RequestParam String email) {
        return userService.removeComment(commentId, email);
    }

    @PostMapping("/resetPass")
    @Operation(
            summary = "Reset Password",
            description = "Allow users to reset their password on the Twitter clone platform.",
            tags = {"User Management"}
    )
    public String resetPassWord(@RequestParam String email){
        return userService.resetPassWord(email);
    }

    @PutMapping("verifyOTP")
    @Operation(
            summary = "Verify OTP",
            description = "Verify the OTP (One-Time Password) sent to the user's email during the password reset process on the Twitter clone platform.",
            tags = {"User Management"}
    )
    public String verifyOTP(@RequestParam String email,String otp,String newPassword) throws NoSuchAlgorithmException {

        return userService.verifyOTP(email,otp,newPassword);
    }

}
