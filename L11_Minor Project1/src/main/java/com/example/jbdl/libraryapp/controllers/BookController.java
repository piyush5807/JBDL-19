package com.example.jbdl.libraryapp.controllers;

import com.example.jbdl.libraryapp.models.Book;
import com.example.jbdl.libraryapp.requests.BookCreateRequest;
import com.example.jbdl.libraryapp.requests.BookUpdateRequest;
import com.example.jbdl.libraryapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

//    @Autowired
    BookService bookService; // Reflections API

    @Autowired
    BookController(BookService bookService){
        this.bookService = bookService;
    }

    // API should not be same - url + method

    @PostMapping("/book")
    public void createBook(@RequestBody BookCreateRequest bookCreateRequest){
        bookService.addBook(bookCreateRequest);
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam("id") int id) throws Exception{
        return bookService.getBookById(id);
    }

    @PutMapping("/book")
    public void updateBook(@RequestParam("book_id") int bookId,
                           @RequestBody BookUpdateRequest bookUpdateRequest){
        bookService.updateBook(bookId, bookUpdateRequest);
    }


    // 1. Student create
    // 2. Issuing a book
    // 3. Returning a book
}
