package com.example.jbdl.libraryapp.controllers;

import com.example.jbdl.libraryapp.models.Student;
import com.example.jbdl.libraryapp.models.User;
import com.example.jbdl.libraryapp.repositories.StudentRepository;
import com.example.jbdl.libraryapp.requests.StudentCreateRequest;
import com.example.jbdl.libraryapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void addStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        studentService.addStudent(studentCreateRequest);
    }


    // It should be called by the student
    @GetMapping("/student")
    public Student getStudent(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if(!user.isStudent()){
            throw new AuthorizationServiceException("Admins can not invoke this API");
        }

        int studentId = user.getUserTypeId(); // 0
        return studentService.getStudent(studentId);
    }

    @GetMapping("/get_student")
    public Student getStudent(@RequestParam("id") int id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if((user.isStudent() && user.getUserTypeId() == id) || !user.isStudent()){
            return studentService.getStudent(id);
        }

        throw new AuthorizationServiceException("user can not invoke this API");


    }

    // it is called by the HR
    @GetMapping("/student/id/{id}")
    public Student getStudentById(@PathVariable("id") int id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if(user.isStudent()){
            throw new AuthorizationServiceException("Students can not invoke this API");
        }

        return studentService.getStudent(id);
    }
}
