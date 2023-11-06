package com.insta.instagram.Controller;

import com.insta.instagram.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PutMapping("user/{id}/{blueTick}")
    public String toggleBlueTick(@PathVariable Long id, @PathVariable boolean blueTick) {
        return adminService.toggleBlueTick(id,blueTick);
    }
}
