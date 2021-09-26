package com.example.jbdl.demojpa.repositories;

import com.example.jbdl.demojpa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
