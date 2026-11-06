package com.example.CurdOperations.repository;

import com.example.CurdOperations.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
