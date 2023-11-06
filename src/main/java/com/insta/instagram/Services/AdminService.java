package com.insta.instagram.Services;

import com.insta.instagram.Model.Admin;
import com.insta.instagram.Repositroy.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UserService userService;
    public String toggleBlueTick(Long id, boolean blueTick) {
        Admin admin = adminRepo.findById(id).orElse(null);

        if (admin != null) {
            admin.setBlueTicked(blueTick);
            adminRepo.save(admin);
            return "Blue tick was set to " + blueTick;
        } else {
            return "Admin doesn't exist";
        }
    }
}
