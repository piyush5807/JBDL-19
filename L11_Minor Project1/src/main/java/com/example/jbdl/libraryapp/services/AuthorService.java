package com.example.jbdl.libraryapp.services;

import com.example.jbdl.libraryapp.models.Author;
import com.example.jbdl.libraryapp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthorByEmail(String email){
        return authorRepository.findByEmail(email);
    }

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }
}
