package com.khwilo.employee_manager.dao;

import com.khwilo.employee_manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsByEmailAddress(String emailAddress);
    Boolean existsByEmailAddressAndPassword(String emailAddress, String password);
}
