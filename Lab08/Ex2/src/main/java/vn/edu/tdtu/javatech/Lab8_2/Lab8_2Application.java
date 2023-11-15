package vn.edu.tdtu.javatech.Lab8_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import vn.edu.tdtu.javatech.Lab8_2.model.Employee;
import vn.edu.tdtu.javatech.Lab8_2.service.EmployeeService;

@SpringBootApplication
public class Lab8_2Application implements CommandLineRunner {
	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(Lab8_2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		employeeService.save(new Employee("Nguyen Hao",
				"haonguyen@mail.com",
				"25, rue Lauriston, Paris, France",
				"(503) 555-9931"));
		employeeService.save(new Employee("Hao Nguyen",
						"nguyenhao@mail.com",
						"C/ Araquil, 67, Madrid, Spain",
						"(204) 619-5731"));
	}
}
