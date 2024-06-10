package com.example.restmongo.service;

import com.example.restmongo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> findById(String id);
    Student createStudent(Student student);
    Student updateStudent(String id, Student updatedStudent);
    void deleteStudent(String id);
}
