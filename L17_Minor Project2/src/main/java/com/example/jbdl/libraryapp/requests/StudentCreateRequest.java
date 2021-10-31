package com.example.jbdl.libraryapp.requests;

import com.example.jbdl.libraryapp.models.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {

    private String name;
    private int age;
    private String country;
    private String contact;
    private String email;
    private String rollNo;

//    private String username; // some unique vector for distinguishing other users
    private String password;

    public Student toStudent(){
        return Student.builder()
                .name(name)
                .age(age)
                .country(country)
                .email(email)
                .rollNo(rollNo)
                .contact(contact)
                .build();
    }
}
