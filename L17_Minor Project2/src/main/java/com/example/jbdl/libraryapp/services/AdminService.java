package com.example.jbdl.libraryapp.services;

import com.example.jbdl.libraryapp.models.Admin;
import com.example.jbdl.libraryapp.models.User;
import com.example.jbdl.libraryapp.repositories.AdminRepository;
import com.example.jbdl.libraryapp.repositories.UserRepository;
import com.example.jbdl.libraryapp.requests.AdminCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${app.security.admin_role}")
    String ADMIN_ROLE;

    @Autowired
    UserRepository userRepository;

    public Admin getAdmin(int adminId){
        return adminRepository.findById(adminId).orElse(null);
    }

    @Transactional
    public void saveAdmin(AdminCreateRequest adminCreateRequest){

        Admin admin = Admin.builder()
                .email(adminCreateRequest.getEmail())
                .name(adminCreateRequest.getName())
                .build();

        admin = adminRepository.save(admin);

        User user = User.builder()
                .username(admin.getEmail())
                .password(passwordEncoder.encode(adminCreateRequest.getPassword()))
                .userTypeId(admin.getId())
                .authorities(ADMIN_ROLE)
                .createdOn(admin.getCreatedOn())
                .build();

        userRepository.save(user);

    }
}
