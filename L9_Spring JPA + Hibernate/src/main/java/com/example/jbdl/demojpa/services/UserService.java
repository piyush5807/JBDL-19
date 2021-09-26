package com.example.jbdl.demojpa.services;

import com.example.jbdl.demojpa.repositories.UserRepository;
import com.example.jbdl.demojpa.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
