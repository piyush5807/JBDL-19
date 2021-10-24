package com.example.jbdl.demosecurityjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppRepository extends JpaRepository<AppUser, Integer> {

//    @Query("select u from AppUser u where u.username = ?1")
    AppUser findByUsername(String user);
}
