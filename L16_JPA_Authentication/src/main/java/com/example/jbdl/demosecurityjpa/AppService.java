package com.example.jbdl.demosecurityjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppService implements UserDetailsService {

    /**
     This function is used to retrieve the use the object from anywhere so that spring security
     can match the passwords given in the request and the one which is present in the particular
     data source
     **/

    @Autowired
    AppRepository appRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedUsername = passwordEncoder.encode(s);
//        System.out.println("encoded username is " + encodedUsername);
        return appRepository.findByUsername(s);
    }

}
