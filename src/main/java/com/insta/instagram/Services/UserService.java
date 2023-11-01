package com.insta.instagram.Services;

import com.insta.instagram.Model.User;
import com.insta.instagram.Model.dto.Credential;
import com.insta.instagram.Repositroy.UserRepo;
import com.insta.instagram.Services.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

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
}
