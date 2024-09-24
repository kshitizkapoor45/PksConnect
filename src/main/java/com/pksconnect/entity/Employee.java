package com.pksconnect.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements UserDetails {

    @Column(unique = true)
    private String employee_code;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;

    private String firstname;

    private String lastname;

    private String password;
     
    @Column(unique = true)
    private String email;

    private String phoneNo;

    private String designation;

    private String Address;

    private String role;

    private LocalDate DateOfBirth;

    private String AadharCardNumber;

    private String BloodGroup;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leaves> leaves = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
        SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(role);
        return Arrays.asList(authorities);
    }

    @Override
    public String getPassword() {
         
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

   

}
