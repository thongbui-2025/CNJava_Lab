package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	private StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome Spring boot");

		Student st1 = new Student(1L, "Huy1", 18, "huy1@gmail.com", 6.5);
		Student st2 = new Student(2L, "Huy2", 18, "huy2@gmail.com", 6.5);
		Student st3 = new Student(3L, "Thong", 18, "thong@gmail.com", 6.5);
		this.studentService.save(st1);
		this.studentService.save(st2);
		this.studentService.save(st3);
		showStudent();
		st1.setIeltsScore(8.0);
		System.out.println("After updating student");
		this.studentService.save(st1);
		showStudent();
		this.studentService.deleteStudent(st1.getId());
		System.out.println("After delete student");
		showStudent();
		
	}

	public void showStudent() {
		List<Student> studentList = (List<Student>) this.studentService.getAllStudent();
		for (Student student: studentList) {
			System.out.println(student);
		}
	}
}
