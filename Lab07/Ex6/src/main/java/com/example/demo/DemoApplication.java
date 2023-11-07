package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	private StudentService studentService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	private void showStudent() {
		List<Student> studentList = (List<Student>) this.studentService.getAllStudents();
		for (Student student: studentList) {
			System.out.println(student);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome Spring boot");

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(1L, "Alex", 18, "alex@tdtu.edu.vn", 6.5));
		studentList.add(new Student(2L, "Bob", 19, "bob@tdtu.edu.vn", 7.0));
		studentList.add(new Student(3L, "Mary", 20, "mary@tdtu.edu.vn", 7.5));
		studentList.add(new Student(4L, "Lauren", 20, "lauren@tdtu.edu.vn", 8.5));
		studentList.add(new Student(5L, "Student5", 21, "student5@tdtu.edu.vn", 8.5));
		studentList.add(new Student(6L, "Student6", 21, "student6@tdtu.edu.vn", 8.5));
		studentList.add(new Student(7L, "Student7", 21, "student7@tdtu.edu.vn", 8.5));
		studentList.add(new Student(8L, "Student8", 21, "student8@tdtu.edu.vn", 8.5));
		studentList.add(new Student(9L, "Student9", 21, "student9@tdtu.edu.vn", 8.5));
		studentList.add(new Student(10L, "Student10", 21, "student10@tdtu.edu.vn", 8.5));
		studentList.add(new Student(11L, "Student11", 21, "student11@tdtu.edu.vn", 8.5));
		for(Student student: studentList) {
			studentService.save(student);
		}
		showStudent();

		System.out.println("The customized list of students:");
		List<Student> customizedStudentList = (List<Student>) studentService.getCustomizedStudentList();
		showStudentList(customizedStudentList);
		
	}



	private void showStudentList(List<Student> studentList) {
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
}
