package com.khwilo.employee_manager;

import com.khwilo.employee_manager.model.Employee;
import com.khwilo.employee_manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagerApplication implements ApplicationRunner {
    @Autowired
    EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Employee employee = new Employee(
                "Frank", "Sinatra", "frank@gmail.com", "sinatra@1234"
        );
        employee.setAdmin(true);
        employeeService.save(employee);
    }
}
