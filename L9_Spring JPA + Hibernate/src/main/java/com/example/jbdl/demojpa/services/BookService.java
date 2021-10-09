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

    public void updateBook(int id, int updatedCost){
        // write while covering custom queries
        bookRepository.updateBook(id, updatedCost);
    }

    public List<Book> findBooksByAuthorName(String author){
        return bookRepository.findBookByAuthorName(author);
    }

    public List<Book> findBooksLessThanCost(int cost){
        return bookRepository.findBooksByCost(cost);
    }

    public List<Book> findBooksByName(String name){
        return bookRepository.findByBookName(name);
    }

    public void deleteBook(long id){
        bookRepository.deleteById(id);
    }
}
