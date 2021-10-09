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
    public void updateBookCostById(@RequestParam("id") int id, @RequestParam("cost") int updatedCost){
        bookService.updateBook(id, updatedCost);
    }

    @GetMapping("/book_by_author")
    public List<Book> getBooksByAuthorName(@RequestParam("author") String author){
        // select * from book where author_name = author
        return bookService.findBooksByAuthorName(author);
    }

    @GetMapping("/book_by_cost")
    public List<Book> getBooksByCost(@RequestParam("cost") int cost){
        // return all the books where cost of the book is < the given cost
        // select * from book where cost < cost

        return bookService.findBooksLessThanCost(cost);
    }

    @GetMapping("/book_by_name")
    public List<Book> getBooksByName(@RequestParam("name") String name){
        // return all the books where cost of the book is < the given cost
        // select * from book where cost < cost

        return bookService.findBooksByName(name);
    }

    @DeleteMapping("/book/{bId}")
    public void deleteBookById(@PathVariable("bId") int id){
        bookService.deleteBook(id);
    }
}
