package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;
public interface StudentService {

    public Iterable<Student> getAllStudent();

    public Student getStudent(Long id) throws Exception;

    public void deleteStudent(Long id);

    public void save(Student student);

    public List<Student> findStudentsWithAgeGreaterThanEqual(int age);

    public Integer countStudentsWithIeltsScore(double score);

    public List<Student> searchStudentsByName(String keyword);
}
