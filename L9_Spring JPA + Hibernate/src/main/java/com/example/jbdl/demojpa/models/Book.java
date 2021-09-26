package com.example.jbdl.demojpa.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "books_table")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "book_name", nullable = false, length = 100)
    private String name;

    private String authorName; // author_name

    private int cost;

    @CreationTimestamp
    private Date bookAddedOn;

}
