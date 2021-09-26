package com.example.jbdl.demojpa.services;

import com.example.jbdl.demojpa.repositories.BookRepository;
import com.example.jbdl.demojpa.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void createBook(Book book){
        bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(long id){
        return bookRepository.findById(id).orElse(null);
    }

    public void updateBook(int id){
        // write while covering custom queries
    }

    public void deleteBook(long id){
        bookRepository.deleteById(id);
    }
}
