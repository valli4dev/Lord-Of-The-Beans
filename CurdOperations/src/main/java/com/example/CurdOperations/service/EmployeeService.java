package com.example.CurdOperations.service;

import com.example.CurdOperations.entity.Employee;
import com.example.CurdOperations.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * Saves a new employee record in the database.
     *
     * @param employee the employee details to be saved
     * @return the saved employee with its generated ID
     */
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    /**
     * Retrieves an employee using the employee ID.
     *
     * @param id the unique ID of the employee
     * @return the employee if found, otherwise null
     */
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    /**
     * Retrieves all employee records from the database.
     *
     * @return a list containing all employees
     */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    /**
     * Searches for an employee using their name.
     *
     * @param name the name of the employee to search for
     * @return the matching employee, or null if no employee is found
     */
    public Employee findByName(String name) {
        return employeeRepository.findByName(name);
    }
    /**
     * Updates the complete details of an existing employee.
     * Updates the employee's name, salary, and department.
     *
     * @param id the unique ID of the employee to be updated
     * @param updatedEmployee the new employee details
     * @return the updated employee record
     */
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setName(updatedEmployee.getName());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setDept(updatedEmployee.getDept());

        return employeeRepository.save(employee);
    }
    /**
     * Updates only the salary of an existing employee.
     *
     * @param id the unique ID of the employee
     * @param salary the new salary to be assigned
     * @return the updated employee record
     */
    public Employee updateSalary(Long id, double salary) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setSalary(salary);
        return employeeRepository.save(employee);
    }
    /**
     * Deletes an employee record using the employee ID.
     *
     * @param id the unique ID of the employee to be deleted
     */
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

}
