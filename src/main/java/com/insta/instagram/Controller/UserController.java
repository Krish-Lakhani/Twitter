package com.insta.instagram.Controller;

import com.insta.instagram.Model.User;
import com.insta.instagram.Model.dto.Credenticial;
import com.insta.instagram.Services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/User")
//@Tag(name = "Greeting", description = "Endpoints for greeting messages")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    private String registerUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.registerUser(user);
    }

    @GetMapping("/login")
    private String loginUser(@RequestBody Credenticial credenticial) throws NoSuchAlgorithmException {
        return userService.loginUser(credenticial);
    }

}
