package com.pksconnect.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pksconnect.entity.Employee;
import com.pksconnect.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo emprepo;

    @Override
    public Employee createEmployee(Employee employee) {
        
        employee.setRole("EMPLOYEE");
        Employee emp = emprepo.save(employee);
       return emp;
    }

    @Override
    public List<Employee> getEmployees() {

        List<Employee> list = emprepo.findAll();
        return list;

    }

    @Override
    public Employee getById(String empCode) {

        Employee emp = emprepo.findById(empCode).orElseThrow();
        return emp;
      
    }

    @Override
    public Employee updatEmployee(Employee employee, String empCode) {
         
        Employee emp = emprepo.findById(empCode).orElseThrow();
        emp.setName(employee.getName());

        Employee updated = emprepo.save(emp);
        return updated;
    }

    @Override
    public void delete(String empCode) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

  



}
