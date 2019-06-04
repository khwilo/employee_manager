package com.khwilo.employee_manager.service;

import com.khwilo.employee_manager.dao.EmployeeRepository;
import com.khwilo.employee_manager.exception.AppException;
import com.khwilo.employee_manager.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new AppException("User with id '" + id + "' not found!")
        );
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
