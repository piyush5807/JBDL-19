package com.example.jbdl.libraryapp.repositories;

import com.example.jbdl.libraryapp.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin, Integer> {


}