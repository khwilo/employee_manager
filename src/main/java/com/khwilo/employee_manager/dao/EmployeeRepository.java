package com.khwilo.employee_manager.dao;

import com.khwilo.employee_manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsEmployeeById(Long id);
    Boolean existsByEmailAddress(String emailAddress);
    Optional<Employee> findEmployeeByEmailAddress(String emailAddress);
}
