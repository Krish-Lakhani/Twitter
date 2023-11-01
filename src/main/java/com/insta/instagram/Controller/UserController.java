package com.insta.instagram.Controller;

import com.insta.instagram.Model.User;
import com.insta.instagram.Model.dto.Credential;
import com.insta.instagram.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/User")
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

}
