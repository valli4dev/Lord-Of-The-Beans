package com.example.CurdOperations.service;

import com.example.CurdOperations.entity.Employee;
import com.example.CurdOperations.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        employee.setName(employee.getName());
        return employeeRepository.save(employee);

    }

//    public Employee findByName(Employee employee) {
//        return employee;
//    }
}
