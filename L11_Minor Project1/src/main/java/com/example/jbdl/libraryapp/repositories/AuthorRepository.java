package com.example.jbdl.libraryapp.repositories;

import com.example.jbdl.libraryapp.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // change the query here
    Author findByEmail(String email);
}
