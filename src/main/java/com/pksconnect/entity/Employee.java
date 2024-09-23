package com.pksconnect.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    private String employee_code;
    
    @Column(unique = true)
    private int emp_id;

    private String name;
     
    @Column(unique = true)
    private String email;

    private String phoneNo;

    private String designation;

    private String Address;

    private String role;

    private LocalDate DateOfBirth;

    private String AadharCardNumber;

    private String BloodGroup;

}
