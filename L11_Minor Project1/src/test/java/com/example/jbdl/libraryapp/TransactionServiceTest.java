package com.example.jbdl.libraryapp;

import com.example.jbdl.libraryapp.models.*;
import com.example.jbdl.libraryapp.repositories.TransactionRepository;
import com.example.jbdl.libraryapp.services.BookService;
import com.example.jbdl.libraryapp.services.StudentService;
import com.example.jbdl.libraryapp.services.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    TransactionService transactionService;

    @Mock
    StudentService studentService;

    @Mock
    BookService bookService;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    public void testIssueBook() throws Exception{

        this.transactionService = new TransactionService(bookService, studentService, transactionRepository, 3, 14, 1);

        Book book = Book.builder()
                .id(2)
                .name("ABC")
                .cost(30)
                .available(true)
                .myStudent(null)
                .build();

        Student student = Student.builder()
                .id(3)
                .books(new ArrayList<>())
                .build();

        Transaction transaction = Transaction.builder()
                .id(1)
                .transactionId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .transactionStatus(TransactionStatus.PENDING)
                .student(student)
                .book(book)
                .build();

        when(bookService.getBookById(2)).thenReturn(book);
        when(studentService.getStudent(3)).thenReturn(student);
        when(transactionRepository.save(any())).thenReturn(transaction);
        when(bookService.addOrUpdateBook(any())).thenReturn(null);

        String transactionId = transactionService.issueBook(2, 3);

        assertEquals(transaction.getTransactionId(), transactionId);

        verify(transactionRepository, times(2)).save(any());
        verify(bookService, times(1)).addOrUpdateBook(any());
        verify(bookService, times(1)).getBookById(anyInt());
        verify(studentService, times(1)).getStudent(anyInt());

    }
}
