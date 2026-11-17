package com.example.CurdOperations.service;

import com.example.CurdOperations.entity.Employee;
import com.example.CurdOperations.exceptions.EmployeeNotFoundException;
import com.example.CurdOperations.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Valli");
        employee.setDept("HR");
        employee.setSalary(250000.0);
        return employee;
    }

    private Employee getEmployee2() {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setName("Ravi");
        employee.setDept("IT");
        employee.setSalary(300000.0);
        return employee;
    }

    @Test
    void testSave() {

        Employee employee = getEmployee();

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee result = employeeService.save(employee);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Valli", result.getName());

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testFindById() {

        Employee employee = getEmployee();

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.findById(1L);

        assertNotNull(result);
        assertEquals("Valli", result.getName());
        assertEquals("HR", result.getDept());
        assertEquals(250000.0, result.getSalary());
    }

    @Test
    void findAll() {
        List<Employee> employeeList = Arrays.asList(
                getEmployee(),
                getEmployee2()
        );
        when(employeeRepository.findAll())
                .thenReturn(employeeList);
        List<Employee> result = employeeService.findAll();
        assertEquals(2, result.size());
        assertEquals("Valli", result.get(0).getName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findByName() {
        Employee employee = getEmployee();
        when(employeeRepository.findByName(employee.getName()))
                .thenReturn(employee);
        Employee result = employeeService.findByName(employee.getName());
        assertEquals("Valli", result.getName());
        verify(employeeRepository, times(1))
                .findByName(employee.getName());
    }

    @Test
    void updateEmployee_EmployeeNotFound() {

        Long id = 100L;

        Employee updatedEmployee = new Employee();

        when(employeeRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.updateEmployee(id, updatedEmployee));

        verify(employeeRepository, times(1)).findById(id);

        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void updateSalary() {
        Long id = 1L;
        double salary = 350000.0;

        Employee employee = getEmployee();

        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(employee));

        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        Employee result = employeeService.updateSalary(id, salary);
        assertEquals(350000.0, result.getSalary());
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void updateEmployee() {

        Long id = 1L;
        Employee employee = getEmployee();
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("Valli Updated");
        updatedEmployee.setSalary(350000.0);
        updatedEmployee.setDept("Development");

        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(employee));

        when(employeeRepository.save(any(Employee.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        Employee result = employeeService.updateEmployee(id, updatedEmployee);
        assertEquals("Valli Updated", result.getName());
        assertEquals(350000.0, result.getSalary());
        assertEquals("Development", result.getDept());
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }
    @Test
    void updateSalary_EmployeeNotFound() {
        Long id = 100L;
        double salary = 350000.0;

        when(employeeRepository.findById(id))
                .thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.updateSalary(id, salary));
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void delete() {
        Long id = 1L;
        employeeService.delete(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }
}

