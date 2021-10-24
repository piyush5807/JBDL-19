package com.example.jbdl.demosecurityjpa;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/student") // student, admin
    public String sayHelloToStudent(@RequestParam("name") String name){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello student " + name;
    }

    @PostMapping("/student") // admin
    public String createStudent(@RequestParam("name") String name){
        return "Creating student " + name;
    }

    @GetMapping("/admin") // admin
    public String sayHelloToAdmin(@RequestParam("name") String name){
        return "Hello admin" + name;
    }

    @GetMapping("/") // everyone
    public String sayHello(@RequestParam("name") String name){
        return "Hello " + name;
    }

//    @PostMapping("/signup")
//    public void signup(UserCreateRequest --> username, password, authorities){
//
//    }
}
