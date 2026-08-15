package com.orderflow.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderflow.entity.Employee;
import com.orderflow.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
    public Employee getEmployeeById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    public Employee updateEmployee(Integer id, Employee employee) {
        employee.setId(id);
        return repository.save(employee);
    }
    public void deleteEmployee(Integer id) {
        repository.deleteById(id);
    }
}