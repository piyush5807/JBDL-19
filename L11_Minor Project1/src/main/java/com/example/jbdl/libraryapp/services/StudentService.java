package com.example.jbdl.libraryapp.services;

import com.example.jbdl.libraryapp.models.Book;
import com.example.jbdl.libraryapp.models.Student;
import com.example.jbdl.libraryapp.repositories.StudentRepository;
import com.example.jbdl.libraryapp.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentCreateRequest studentCreateRequest){

        Student student = studentCreateRequest.toStudent();
        studentRepository.save(student);
    }

    public Student getStudent(int id){
        return studentRepository.findById(id).orElse(null);
    }

    public void addOrUpdateStudent(Student student){
        studentRepository.save(student);
    }
}
