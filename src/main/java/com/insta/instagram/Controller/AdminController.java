package com.insta.instagram.Controller;

import com.insta.instagram.Services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@Tag(name = "Admin Management", description = "Manage administrative tasks and operations securely, including toggling the blue tick status for users.")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PutMapping("user/{id}/{blueTick}")
    @Operation(
            summary = "Toggle Blue Tick Status",
            description = "Toggle the blue tick status for a specific user by providing the user ID and the new blue tick status.",
            tags = {"Admin Management"}
    )
    public String toggleBlueTick(@PathVariable Long id, @PathVariable boolean blueTick) {
        return adminService.toggleBlueTick(id, blueTick);
    }
}