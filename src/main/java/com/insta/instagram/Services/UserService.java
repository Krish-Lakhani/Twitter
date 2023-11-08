package com.insta.instagram.Services;

import com.insta.instagram.Model.*;
import com.insta.instagram.Model.dto.Credential;
import com.insta.instagram.Model.dto.PostDto;
import com.insta.instagram.Repositroy.AdminRepo;
import com.insta.instagram.Repositroy.PostRepo;
import com.insta.instagram.Repositroy.UserRepo;
import com.insta.instagram.Services.utility.OTPGenerator;
import com.insta.instagram.Services.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    @Autowired
    FollowService followService;

    @Autowired
    CommentService commentService;

    @Autowired
    EmailService emailService;

    @Autowired
    PostRepo postRepo;

    @Autowired
    AdminRepo adminRepo;

    public String SignUp(User user) throws NoSuchAlgorithmException {

        if (userRepo.existsByuserEmail(user.getUserEmail())) {
            return "Already Register";
        }

        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(user.getUserPassword());
        user.setUserPassword(hashPass);
        userRepo.save(user);
        return "Register Successfully";

    }

    public String SignIn(Credential credential) throws NoSuchAlgorithmException {
        if (!userRepo.existsByuserEmail(credential.getEmail())) {
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
        if (user.getStatus().equals("login")) {
            user.setStatus("logout");
            userRepo.save(user);
        } else {
            return "Please signIn first";
        }
        return "User Signed out successfully";
    }

    public String CreatePost(Post post, String email) {
        User user = userRepo.findByUserEmail(email);
        if (user.getStatus().equals("login")) {
            User postOwner = userRepo.findByUserEmail(email);
            post.setPostOwner(postOwner);
            postOwner.setTotal(postOwner.getTotal() + 1);
            postService.CreatePost(post);
        } else {
            return "Please signIn first";
        }
        return "Post Upload Successfully";
    }

    public String deletePost(Integer postId, String email) {
        User user = userRepo.findByUserEmail(email);
        if (user.getStatus().equals("login") && user.getTotal() > 0) {
            user.setTotal(user.getTotal() - 1);
            postService.deletePost(postId, user);
        } else {
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

    public String totalLike(Integer postId) {
        Post validPost = postService.getPostById(postId);

        if (validPost != null) {
            Integer likeCountForPost = likeService.getLikeCountForPost(validPost);
            return String.valueOf(likeCountForPost);
        } else {
            return "Cannot like on Invalid Post!!";
        }
    }

    public String totalComment(Integer postId) {
        Post validPost = postService.getPostById(postId);

        if (validPost != null) {
            Integer CommentCountForPost = commentService.getCommentCountForPost(validPost);
            return String.valueOf(CommentCountForPost);
        } else {
            return "Cannot Comment on Invalid Post!!";
        }
    }

    public String deleteLike(Integer likeId, String likerEmail) {
        Like like = likeService.findLike(likeId);
        if (like != null) {
            if (authorizeLikeRemover(likerEmail, like)) {
                likeService.removeLike(like);
                return "like deleted successfully";
            } else {
                return "Like is already detected...Not allowed!!!!";
            }
        }
        return "Invalid like";
    }

    private boolean authorizeLikeRemover(String likerEmail, Like like) {
        String likeOwnerEmail = like.getLiker().getUserEmail();
        return likerEmail.equals(likeOwnerEmail);
    }

    public String FollowUser(Follow follow, String followerEmail) {
        User followTargetUser = userRepo.findById(follow.getCurrentUser().getUserid()).orElse(null);
        User follower = userRepo.findByUserEmail(followerEmail);

        if (followTargetUser != null) {
            if (followService.isFollowAllowed(followTargetUser, follower)) {
                followService.startFollowing(follow, follower);
                return follower.getGetUserHandle() + " is now following " + followTargetUser.getGetUserHandle();
            } else {
                return follower.getUserHandle + " already follows " + followTargetUser.getUserHandle;
            }
        } else {
            return "User to be followed is Invalid!!!";
        }
    }

    public String unFollowUser(Integer followId, String followerEmail) {
        Follow follow = followService.findFollow(followId);
        if (follow != null) {
            if (authorizeUnfollow(followerEmail, follow)) {
                followService.unfollow(follow);
                return follow.getCurrentUser().getGetUserHandle() + " Unfollowing " + follow.getUserFollower().getGetUserHandle();
            } else {
                return "Unauthorized unfollow detected...Not allowed!!!!";
            }
        } else {
            return "Invalid follow mapping";
        }
    }

    private boolean authorizeUnfollow(String email, Follow follow) {
        String targetEmail = follow.getCurrentUser().getUserEmail();
        String followerEmail = follow.getUserFollower().getUserEmail();

        return targetEmail.equals(email) || followerEmail.equals(email);
    }

    public String addComment(Comment comment, String commenterEmail) {
        boolean postValid = postService.validatePost(comment.getTwitterPost());
        if (postValid) {
            User commenter = userRepo.findByUserEmail(commenterEmail);
            comment.setCommenter(commenter);
            return commentService.addComment(comment);
        } else {
            return "Cannot comment on Invalid Post!!";
        }
    }


    public String removeComment(Integer commentId, String email) {
        Comment comment = commentService.findComment(commentId);
        if (comment != null) {
            if (authorizeCommentRemover(email, comment)) {
                commentService.removeComment(comment);
                return "comment deleted successfully";
            } else {
                return "Unauthorized delete detected...Not allowed!!!!";
            }

        } else {
            return "Invalid Comment";
        }
    }

    private boolean authorizeCommentRemover(String email, Comment comment) {
        String commentOwnerEmail = comment.getCommenter().getUserEmail();
        String postOwnerEmail = comment.getTwitterPost().getPostOwner().getUserEmail();

        return postOwnerEmail.equals(email) || commentOwnerEmail.equals(email);
    }

    public String resetPassWord(String email) {
        if (!userRepo.existsByuserEmail(email)) {
            return "Register First";
        }
        User user = userRepo.findByUserEmail(email);
        user.setOtp(OTPGenerator.generateOTP());
        userRepo.save(user);
        emailService.sendOtpEmail(email, user.getOtp());
        return "Otp sent Successfully";
    }

    public String verifyOTP(String email, String otp, String newPassword) throws NoSuchAlgorithmException {
        User user = userRepo.findByUserEmail(email);
        if (user.getOtp().equals(otp)) {
            String newHashPassWord = PasswordEncrypter.hashPasswordWithStaticSecret(newPassword);
            user.setUserPassword(newHashPassWord);
            user.setStatus("logOut");
            userRepo.save(user);
            return "PassWord Successfully Save";
        }
        return "Invalid OTP";
    }

    public List<PostDto> showPost(String email) {
        List<Post> posts = postRepo.findByPostOwnerUserEmail(email);
        return posts.stream().map(post -> new PostDto(post.getTitle(), post.getDescription(), post.getUrl(), post.getTime(), post.getPostOwner().getUserName())).collect(Collectors.toList());
    }

    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public String toggleBlueTick(Long id, boolean blueTick) {
        User user = userRepo.findById(id).orElse(null);
        Admin admin = new Admin();

        if (user != null && blueTick == true) {
            user.setIsBlueTicked("✅");
            admin.setEmail(user.getUserEmail());
            admin.setUserName(user.getUserName());
            adminRepo.save(admin);
            userRepo.save(user);
            return "Blue tick was set..";
        } else if (user != null && blueTick == false) {
            user.setIsBlueTicked("Not Verified");
            userRepo.save(user);
            return "Blue tick was not set..";
        }
        return "user doesn't exist";
    }
}
