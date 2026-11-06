package com.example.CurdOperations.controller;

import com.example.CurdOperations.entity.Employee;
import com.example.CurdOperations.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/Employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveData")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
//    @GetMapping
//    public Employee findByEmail(String email) {
//        return employeeService.findByName()
//    }
}
