package com.pksconnect.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pksconnect.entity.Employee;
import com.pksconnect.repository.EmployeeRepo;
import com.pksconnect.security.JwtService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo emprepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Employee createEmployee(Employee employee) {
        
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee emp = emprepo.save(employee);
        
        String empCode = getEmployeeCode(emp);
        emp.setEmployee_code(empCode);

        Employee saveEmployee = emprepo.save(emp);
       return saveEmployee;
    }

    @Override
    public List<Employee> getEmployees() {

        List<Employee> list = emprepo.findAll();
        return list;

    }

    @Override
    public Employee getById( int empId) {

        Employee emp = emprepo.findById(empId).orElseThrow(() -> new UsernameNotFoundException("Employee not found: "+empId));
        return emp;
      
    }

    @Override
    public Employee updatEmployee(Employee employee,  int empId) {
         
        Employee emp = emprepo.findById(empId).orElseThrow(() -> new UsernameNotFoundException("Employee not found: "+empId));
        emp.setFirstname(employee.getFirstname());
        emp.setLastname(employee.getLastname());
        emp.setEmail(employee.getEmail());
        emp.setPassword(passwordEncoder.encode(employee.getPassword()));
        emp.setPhoneNo(employee.getPhoneNo());
        emp.setAddress(employee.getAddress());
        emp.setBloodGroup(employee.getBloodGroup());
    

        Employee updated = emprepo.save(emp);
        return updated;
    }

    @Override
    public void delete(int empId) {

        Employee emp = emprepo.findById(empId).orElseThrow(() -> new UsernameNotFoundException("Employee not found: "+empId));
        emprepo.delete(emp);
       
    }

    @Override
    public String getEmployeeCode(Employee employee) {

        String firstName = employee.getFirstname();
        int empId = employee.getEmp_id();

        String employeeCode = firstName+empId;
        return employeeCode;
        
    }
    @Override
    public String LoginEmployee(Employee employee)
    {
       Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword()));

       if(authentication.isAuthenticated())
       {
        return jwtService.generateToken(employee.getEmail());
       }
       else{
         throw new BadCredentialsException("Invalid Email or Password");
       }


    }

    @Override
    public Employee changeRole(Employee employee, int empId) {

        Employee emp = emprepo.findById(empId).orElseThrow(() -> new UsernameNotFoundException("Employee not found: "+empId));
        emp.setRole(employee.getRole());

        Employee newRole = emprepo.save(emp);
        return newRole;
        

    }

  



}
