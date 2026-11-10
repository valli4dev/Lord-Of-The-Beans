package com.example.CurdOperations.controller;

import com.example.CurdOperations.entity.Employee;
import com.example.CurdOperations.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Handles HTTP POST requests to save a new employee.
     *
     * @param employee employee details received from the request body
     * @return the saved employee details
     */
    @PostMapping("/saveData")
    public Employee save(@RequestBody Employee employee) {

        return employeeService.save(employee);
    }

    /**
     * Handles HTTP GET requests to retrieve an employee by ID.
     *
     * @param id the employee ID received as a request parameter
     * @return the matching employee details
     */
    @GetMapping("/findId")
    public Employee findById(@RequestParam("id") Long id) {
        return employeeService.findById(id);
    }

    /**
     * Handles HTTP GET requests to retrieve all employees.
     *
     * @return a list of all employee records
     */
    @GetMapping("/findAll")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    /**
     * Handles HTTP GET requests to search for an employee by name.
     *
     * @param name the employee name received as a request parameter
     * @return the matching employee details
     */
    @GetMapping("/findName")
    public Employee findByName(@RequestParam("name") String name) {
        return employeeService.findByName(name);
    }

    /**
     * Handles HTTP PATCH requests to update only an employee's salary.
     *
     * @param id     the employee ID received from the URL path
     * @param salary the new salary received as a request parameter
     * @return the employee record with the updated salary
     */
    @PatchMapping("/updateSalary/{id}")
    public Employee updateSalary(@PathVariable Long id,
                                 @RequestParam double salary) {
        return employeeService.updateSalary(id, salary);
    }

    /**
     * Handles HTTP PUT requests to update the complete employee details.
     *
     * @param id       the employee ID received from the URL path
     * @param employee the updated employee details received from the request body
     * @return the updated employee record
     */
    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    /**
     * Handles HTTP DELETE requests to delete an employee by ID.
     *
     * @param id the unique ID of the employee to be deleted
     */
    @DeleteMapping("/deleteEmployee/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}

