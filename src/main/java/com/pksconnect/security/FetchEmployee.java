package com.pksconnect.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pksconnect.entity.Employee;
import com.pksconnect.repository.EmployeeRepo;

@Service
public class FetchEmployee implements UserDetailsService {

    @Autowired
     private EmployeeRepo empRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee emp = empRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Employee not found"));
        return emp;
       
    }

}
