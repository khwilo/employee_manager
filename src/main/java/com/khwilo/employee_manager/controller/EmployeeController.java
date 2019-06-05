package com.khwilo.employee_manager.controller;

import com.khwilo.employee_manager.payload.ApiResponse;
import com.khwilo.employee_manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<Object> fetchAllEmployees() {
       if (employeeService.getAllEmployees().isEmpty()) {
           return new ResponseEntity<>(
                   new ApiResponse(404, "No employee is present!"),
                   HttpStatus.NOT_FOUND
           );
       }

       return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Object> fetchAllEmployeesWithRoles() {
        if (employeeService.getAllEmployeesWithRoles().isEmpty()) {
            return new ResponseEntity<>(
                    new ApiResponse(404, "Employee Role is not set!"),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(employeeService.getAllEmployeesWithRoles(), HttpStatus.OK);
    }
}
