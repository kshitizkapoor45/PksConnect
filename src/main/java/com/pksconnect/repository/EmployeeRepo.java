package com.pksconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pksconnect.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

}


