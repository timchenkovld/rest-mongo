package com.example.restmongo.controller;

import com.example.restmongo.exception.EntityNotFoundException;
import com.example.restmongo.model.Student;
import com.example.restmongo.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentController {
    private final StudentServiceImpl service;

    @GetMapping("/students")
    public List<Student> fetchAllStudents() {
        return service.getAllStudents();
    }


    @PostMapping("/save")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            service.createStudent(student);
            return ResponseEntity.ok("Student created successfully");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Optional<Student> studentOptional = service.findById(id);
        return studentOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable String id, @RequestBody Student updatedStudent) {
        try {
            service.updateStudent(id, updatedStudent);
            return ResponseEntity.ok("Student updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable String id) {
        Optional<Student> studentOptional = service.findById(id);
        if (studentOptional.isPresent()) {
            service.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
