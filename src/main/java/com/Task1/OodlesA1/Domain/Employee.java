package com.Task1.OodlesA1.Domain;

import com.Task1.OodlesA1.Enums.DepartmentType;
import com.Task1.OodlesA1.Enums.Designation;
import com.Task1.OodlesA1.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
@Data
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eId;

    private String empName;

    private Integer age;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    private DepartmentType departmentType;

     @OneToOne
     @JoinColumn
     Company company;

     @ManyToOne
     @JoinColumn
     Department department;






}
