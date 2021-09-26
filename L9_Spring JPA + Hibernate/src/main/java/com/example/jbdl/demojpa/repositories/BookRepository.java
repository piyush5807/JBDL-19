package com.example.jbdl.demojpa.repositories;

import com.example.jbdl.demojpa.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
