package com.example.jbdl.libraryapp.controllers;

import com.example.jbdl.libraryapp.models.Student;
import com.example.jbdl.libraryapp.repositories.StudentRepository;
import com.example.jbdl.libraryapp.requests.StudentCreateRequest;
import com.example.jbdl.libraryapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void addStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        studentService.addStudent(studentCreateRequest);
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam("id") int id){
        return studentService.getStudent(id);
    }
}
