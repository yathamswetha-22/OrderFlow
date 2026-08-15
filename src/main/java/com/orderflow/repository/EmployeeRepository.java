package com.orderflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orderflow.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
