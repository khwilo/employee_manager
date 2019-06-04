package com.khwilo.employee_manager.controller;

import com.khwilo.employee_manager.dao.EmployeeRepository;
import com.khwilo.employee_manager.model.Employee;
import com.khwilo.employee_manager.payload.ApiResponse;
import com.khwilo.employee_manager.payload.SignUpRequest;
import com.khwilo.employee_manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerEmployee(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (employeeRepository.existsByEmailAddress(signUpRequest.getEmailAddress())) {
            return new ResponseEntity<>(
                    new ApiResponse(409, "Email address is already in use!"),
                    HttpStatus.CONFLICT
            );
        }

        Employee employee = new Employee(
                signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmailAddress(), signUpRequest.getPassword()
        );

        employeeService.save(employee);

        return new ResponseEntity<>(
                new ApiResponse(201, "Employee account creation is successful!"),
                HttpStatus.CREATED
        );
    }
}
