package com.pksconnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pksconnect.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByEmail(String email);

}


