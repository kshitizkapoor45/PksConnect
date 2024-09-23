package com.pksconnect.service;

import java.util.List;

import com.pksconnect.entity.Employee;

public interface EmployeeService {

    public Employee createEmployee(Employee employee);

    public List<Employee> getEmployees();

    public Employee getById(String empCode);

    public Employee updatEmployee(Employee employee, String empCode);

    public void delete(String empCode);

}
