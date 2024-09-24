package com.pksconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pksconnect.entity.Employee;
import com.pksconnect.service.EmployeeService;
import com.pksconnect.util.Response;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/pksconnect")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @PostMapping("/newEmployee")
    @PreAuthorize("hasAuthority('ADMIN')")   
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee,
                                       @RequestParam(value = "role", defaultValue = "EMPLOYEE", required = false) String role)
    {
        employee.setRole(role);
        Employee emp = empService.createEmployee(employee);
        return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> employees = empService.getEmployees();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  int empId)
    {
        Employee emp = empService.getById(empId);
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);

    }

    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable  int empId)
    {
        Employee emp = empService.updatEmployee(employee, empId);
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);

    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable  int empId)
    {
        empService.delete(empId);
        return new ResponseEntity<Response>(new Response("Employee Deleted", true),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginEmployee(@RequestBody Employee employee)
    {
        String message = empService.LoginEmployee(employee);
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }

    @PutMapping("/roleChange")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Employee> changeOfRole(@RequestBody Employee employee, @PathVariable int empId)
    {
        Employee emp = empService.changeRole(employee, empId);
        return new ResponseEntity<Employee>(emp, HttpStatus.OK); 
    }

}
