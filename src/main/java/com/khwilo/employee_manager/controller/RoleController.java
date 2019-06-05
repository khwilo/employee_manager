package com.khwilo.employee_manager.controller;

import com.khwilo.employee_manager.dao.EmployeeRepository;
import com.khwilo.employee_manager.model.Employee;
import com.khwilo.employee_manager.model.Role;
import com.khwilo.employee_manager.payload.ApiResponse;
import com.khwilo.employee_manager.payload.RoleRequest;
import com.khwilo.employee_manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create/{id}")
    public ResponseEntity<Object> createRole(
            @PathVariable("id") String id, @Valid @RequestBody RoleRequest roleRequest) {
        Long employeeId = Long.parseLong(id);
        if (!employeeRepository.existsById(employeeId)) {
            return new ResponseEntity<>(
                    new ApiResponse(404, "Employee not found!"),
                    HttpStatus.NOT_FOUND
            );
        }

        if (!employeeRepository.existsByEmailAddress(roleRequest.getEmailAddress())) {
            return new ResponseEntity<>(
                    new ApiResponse(404, "Employee not found!"),
                    HttpStatus.NOT_FOUND
            );
        }

        Employee foundEmployee = employeeService.getEmployeeByEmailAddress(roleRequest.getEmailAddress());
        if (!foundEmployee.isAdmin()) {
            return new ResponseEntity<>(
                    new ApiResponse(401, "This action requires admin access!"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        Role role = new Role(roleRequest.getRole());

        Employee employee = employeeService.getEmployeeById(employeeId);

        employee.setRole(role);
        role.setEmployee(employee);

        employeeService.save(employee);

        return new ResponseEntity<>(
                new ApiResponse(201, "Role successfully created!"),
                HttpStatus.CREATED
        );
    }
}
