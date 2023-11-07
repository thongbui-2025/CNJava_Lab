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
		Student st2 = new Student(2L, "Huy2", 20, "huy2@gmail.com", 8.5);
		Student st3 = new Student(3L, "Thong", 19, "thong@gmail.com", 7.5);
		this.studentService.save(st1);
		this.studentService.save(st2);
		this.studentService.save(st3);
		showStudent();
		List<Student> studentList = studentService.findStudentsWithAgeGreaterThanEqual(18);
		System.out.println("Students with age greater than or equal to 19:");
		showStudentList(studentList);
		System.out.println("The number of students with Ielts score of 7.0 is " + studentService.countStudentsWithIeltsScore(7.0));
		studentList = studentService.searchStudentsByName("Thong");
		System.out.println("The students are found. Their information is: ");
		showStudentList(studentList);
		
	}

	private void showStudent() {
		List<Student> studentList = (List<Student>) this.studentService.getAllStudent();
		for (Student student: studentList) {
			System.out.println(student);
		}
	}

	private void showStudentList(List<Student> studentList) {
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
}
