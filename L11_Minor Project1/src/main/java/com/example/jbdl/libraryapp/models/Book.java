package com.example.jbdl.libraryapp.models;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private int cost;
    private boolean available;

    @CreationTimestamp
    private Date bookAddedOn;

    private Author author;

    private Student student;

    private List<Transaction> transactions;
}
