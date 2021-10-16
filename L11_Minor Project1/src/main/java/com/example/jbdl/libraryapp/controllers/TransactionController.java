package com.example.jbdl.libraryapp.controllers;

import com.example.jbdl.libraryapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    // student id = 2, on behalf of student id 1

    @PostMapping("/issue_book")
    public String issueBook(@RequestParam("book_id") int bookId,
                            @RequestParam("student_id") int studentId) throws Exception {

       return transactionService.issueBook(bookId, studentId);
    }

    @GetMapping("/fine")
    public Integer getFine(@RequestParam("book_id") int bookId,
                           @RequestParam("student_id") int studentId){

        return transactionService.getFine(bookId, studentId);
    }

    @PostMapping("/return_book")
    public String returnBook(@RequestParam("book_id") int bookId,
                             @RequestParam("student_id") int studentId,
                             @RequestParam("fine") int fine) throws ChangeSetPersister.NotFoundException {
        return transactionService.returnBook(bookId, studentId, fine);
    }

    // Q1. For a given student, get all the transactions in last week in a csv file
}
