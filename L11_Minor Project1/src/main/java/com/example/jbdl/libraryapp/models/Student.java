package com.example.jbdl.libraryapp.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private String country;

    @Column(unique = true)
    private String contact;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String rollNo;

    @CreationTimestamp
    private Date registeredOn;

    private List<Book> books;

    private List<Transaction> transactions;

    private int totalFine;

}
