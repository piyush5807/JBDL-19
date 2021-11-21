package com.example.jbdl.major_project;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(unique = true, nullable = false)
    private String email; // unique vector

    @Column(unique = true, nullable = false)
    private String phone; // unique vector
    private int age;
}
