package com.example.jbdl.libraryapp.repositories;

import com.example.jbdl.libraryapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String user);
}
