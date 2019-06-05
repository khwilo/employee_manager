package com.khwilo.employee_manager.service;

import com.khwilo.employee_manager.dao.EmployeeRepository;
import com.khwilo.employee_manager.exception.AppException;
import com.khwilo.employee_manager.model.Employee;
import com.khwilo.employee_manager.payload.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(
                employee -> employees.add(employee)
        );

        return employees;
    }

    public List<EmployeeResponse> getAllEmployeesWithRoles() {
        List<Employee> employees = getAllEmployees();
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();

        for (Employee e: employees) {
            try {
                EmployeeResponse employeeResponse = new EmployeeResponse(
                        e.getFirstName(), e.getLastName(), e.getEmailAddress(), e.getRole().getRole()
                );
                employeeResponseList.add(employeeResponse);
            } catch (NullPointerException ex) {
                return Collections.EMPTY_LIST;
            }

        }

        return employeeResponseList;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new AppException("Employee with id '" + id + "' not found!")
        );
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
