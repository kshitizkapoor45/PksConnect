package com.pksconnect.service;

import java.util.List;

import com.pksconnect.entity.Employee;

public interface EmployeeService {

    public Employee createEmployee(Employee employee);

    public List<Employee> getEmployees();

    public Employee getById(int empId);

    public Employee updatEmployee(Employee employee, int empId);

    public void delete(int empId);

    public String getEmployeeCode(Employee employee);

    public String LoginEmployee(Employee employee);

    public Employee changeRole(Employee employee, int empId);

}
