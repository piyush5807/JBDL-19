package com.example.jbdl.libraryapp.services;

import com.example.jbdl.libraryapp.models.User;
import com.example.jbdl.libraryapp.repositories.UserCacheRepository;
import com.example.jbdl.libraryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCacheRepository userCacheRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userCacheRepository.fetchUserFromCache(s);
        if(user != null){
            return user;
        }

        user = userRepository.findByUsername(s);
        userCacheRepository.saveUserInCache(user);
        return user;
    }
}
