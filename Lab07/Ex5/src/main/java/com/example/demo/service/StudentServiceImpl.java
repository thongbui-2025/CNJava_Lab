package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long id) throws Exception {
        return studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student not found"));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> searchByAge(int age) {
        return (List<Student>) studentRepository.searchByAge(age);
    }

    @Override
    public Integer countStudentsWithIeltsScore(double score) {
        return studentRepository.searchByIeltsScore(score).size();
    }

    @Override
    public List<Student> searchStudentsByName(String keyword) {
        return studentRepository.searchByName(keyword);
    }


}
