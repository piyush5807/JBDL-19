package com.example.jbdl.libraryapp.services;

import com.example.jbdl.libraryapp.models.Student;
import com.example.jbdl.libraryapp.models.User;
import com.example.jbdl.libraryapp.repositories.StudentRepository;
import com.example.jbdl.libraryapp.repositories.UserRepository;
import com.example.jbdl.libraryapp.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    StudentRepository studentRepository;
    UserRepository userRepository;
    private String STUDENT_ROLE;
    PasswordEncoder passwordEncoder;


    @Autowired
    public StudentService(StudentRepository studentRepository,
                          UserRepository userRepository,
                          @Value("${app.security.student_role}") String studentRole,
                          PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.STUDENT_ROLE = studentRole;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addStudent(StudentCreateRequest studentCreateRequest){

        Student student = studentCreateRequest.toStudent();
        student = studentRepository.save(student);

        User user = User.builder()
                .username(studentCreateRequest.getEmail())
                .password(passwordEncoder.encode(studentCreateRequest.getPassword()))
                .userTypeId(student.getId())
                .authorities(STUDENT_ROLE)
                .isStudent(true)
                .createdOn(student.getRegisteredOn())
                .build();

        userRepository.save(user);
    }

    public Student getStudent(int id){
        return studentRepository.findById(id).orElse(null);
    }

    public void addOrUpdateStudent(Student student){
        studentRepository.save(student);
    }
}
