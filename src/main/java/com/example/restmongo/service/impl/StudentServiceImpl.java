package com.example.restmongo.service.impl;

import com.example.restmongo.model.Student;
import com.example.restmongo.repository.StudentRepository;
import com.example.restmongo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Student createStudent(Student student) {
        if (repository.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new DuplicateKeyException("Email must be unique");
        }
        return repository.save(student);
    }

    @Override
    public Student updateStudent(String id, Student updatedStudent) {
        Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setFirstname(updatedStudent.getFirstname());
        existingStudent.setLastname(updatedStudent.getLastname());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setGender(updatedStudent.getGender());
        existingStudent.setAddress(updatedStudent.getAddress());
        existingStudent.setFavouriteSubjects(updatedStudent.getFavouriteSubjects());
        existingStudent.setTotalSpentInBooks(updatedStudent.getTotalSpentInBooks());
        existingStudent.setCreated(updatedStudent.getCreated());

        return repository.save(existingStudent);
    }

    @Override
    public void deleteStudent(String id) {
        repository.deleteById(id);
    }
}
