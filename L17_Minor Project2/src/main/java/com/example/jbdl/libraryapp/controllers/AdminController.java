package com.example.jbdl.libraryapp.controllers;

import com.example.jbdl.libraryapp.models.Admin;
import com.example.jbdl.libraryapp.models.User;
import com.example.jbdl.libraryapp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public Admin getAdminDetails(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if(user.isStudent()){
            throw new AuthorizationServiceException("Students can not see the admin details");
        }

        int adminId = user.getUserTypeId();

        return adminService.getAdmin(adminId);
    }
}
