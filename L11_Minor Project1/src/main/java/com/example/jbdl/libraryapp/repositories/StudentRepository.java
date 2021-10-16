package com.example.jbdl.libraryapp.repositories;

import com.example.jbdl.libraryapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
