package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    public List<Student> findByAgeGreaterThanEqual(Integer age);

    public List<Student> findByIeltsScore(Double ieltsScore);

    public List<Student> findByNameContaining(String keyword);


}
