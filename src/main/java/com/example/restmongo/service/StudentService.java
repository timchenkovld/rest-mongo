package com.example.restmongo.service;

import com.example.restmongo.model.Student;
import com.example.restmongo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}
