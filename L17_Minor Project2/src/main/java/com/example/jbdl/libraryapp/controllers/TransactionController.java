package com.example.jbdl.libraryapp.controllers;

import com.example.jbdl.libraryapp.models.User;
import com.example.jbdl.libraryapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    // student id = 2, on behalf of student id 1

    @PostMapping("/transaction/issue_book")
    public String issueBook(@RequestParam("book_id") int bookId) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if(!user.isStudent()){
            throw new AuthorizationServiceException("Admins can not issue book");
        }

        int studentId = user.getUserTypeId();

        return transactionService.issueBook(bookId, studentId);
    }

    // student
    @GetMapping("/transaction/fine")
    public Integer getFine(@RequestParam("book_id") int bookId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if(!user.isStudent()){
            throw new AuthorizationServiceException("Students can not access this resource");
        }

        int studentId = user.getUserTypeId();

        return transactionService.getFine(bookId, studentId);
    }


    // admin
    @GetMapping("/transaction/fine/student_id/{id}")
    public Integer getFineByStudentId(@RequestParam("book_id") int bookId,
                           @PathVariable("id") int studentId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if(user.isStudent()){
            throw new AuthorizationServiceException("Students can not access this resource");
        }

        return transactionService.getFine(bookId, studentId);
    }


    @PostMapping("/transaction/return_book")
    public String returnBook(@RequestParam("book_id") int bookId,
                             @RequestParam("fine") int fine) throws ChangeSetPersister.NotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if(!user.isStudent()){
            throw new AuthorizationServiceException("Admins can not return book");
        }

        int studentId = user.getUserTypeId();

        return transactionService.returnBook(bookId, studentId, fine);
    }

    // Q1. For a given student, get all the transactions in last week in a csv file
}
