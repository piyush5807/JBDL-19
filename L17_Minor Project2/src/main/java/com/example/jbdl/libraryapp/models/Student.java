package com.example.jbdl.libraryapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private String country;

    @Column(unique = true, nullable = false)
    private String contact;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String rollNo;

    @CreationTimestamp
    private Date registeredOn;

    @OneToMany(mappedBy = "myStudent")
    @JsonIgnoreProperties("myStudent")
    private List<Book> books;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties("student")
    private List<Transaction> transactions;

    private int totalFine;

}
