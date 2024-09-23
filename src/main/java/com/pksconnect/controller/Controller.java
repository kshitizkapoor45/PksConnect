package com.pksconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pksconnect.entity.Employee;
import com.pksconnect.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/pksconnect")
public class Controller {

    @Autowired
    private EmployeeService empService;

    @PostMapping("/")   
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee)
    {
        Employee emp = empService.createEmployee(employee);
        return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> employees = empService.getEmployees();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

}
