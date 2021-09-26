package com.example.jbdl.demojpa.controllers;

import com.example.jbdl.demojpa.models.User;
import com.example.jbdl.demojpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    // update the book's cost to cost + 100 if author = 'Jim';
}
