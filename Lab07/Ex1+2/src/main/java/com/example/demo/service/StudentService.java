package com.example.demo.service;

import com.example.demo.model.Student;

public interface StudentService {

    public Iterable<Student> getAllStudent();

    public Student getStudent(Long id) throws Exception;

    public void deleteStudent(Long id);

    public void save(Student student);
}
