package com.example.jbdl.demojpa.controllers;

import com.example.jbdl.demojpa.models.Book;
import com.example.jbdl.demojpa.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody Book book){
        bookService.createBook(book);
    }

    @GetMapping("/book/{book_id}")
    public Book getBookById(@PathVariable("book_id") long bookId){
        return bookService.getBook(bookId);
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @PutMapping("/book")
    public void updateBookById(@RequestParam("id") int id){
        bookService.updateBook(id);
    }

    @DeleteMapping("/book/{bId}")
    public void deleteBookById(@PathVariable("bId") int id){
        bookService.deleteBook(id);
    }
}
