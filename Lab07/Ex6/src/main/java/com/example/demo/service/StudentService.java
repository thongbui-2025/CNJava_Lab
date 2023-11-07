package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;
public interface StudentService {

    public Iterable<Student> getAllStudents();

    public Iterable<Student> getCustomizedStudentList();

    public Student save(Student student);
}
