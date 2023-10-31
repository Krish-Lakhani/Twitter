package com.insta.instagram.Services;

import com.insta.instagram.Model.User;
import com.insta.instagram.Model.dto.Credenticial;
import com.insta.instagram.Repositroy.UserRepo;
import com.insta.instagram.Services.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public String registerUser(User user) throws NoSuchAlgorithmException {

        if (userRepo.existsByuserEmail(user.getUserEmail())){
            return "Already Register";
        }
        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(user.getUserPassword());
        user.setUserPassword(hashPass);
        userRepo.save(user);
        return "Register Successfully";
    }

    public String loginUser(Credenticial credenticial) throws NoSuchAlgorithmException {
        if (!userRepo.existsByuserEmail(credenticial.getEmail())){
            return "Please Create a Account";
        }
        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(credenticial.getPassword());

        User user = userRepo.findByUserEmail(credenticial.getEmail());


        if (hashPass.equals(user.getUserPassword())) {
            return "login";
        } else {
            return "Credenticial MisMatch";
        }
    }
}
