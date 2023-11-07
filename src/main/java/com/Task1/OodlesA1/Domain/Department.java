package com.Task1.OodlesA1.Domain;

import com.Task1.OodlesA1.Enums.DepartmentType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="department")
@Data
public class Department {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dId;

    private Long noOFEmployees;
    @Enumerated(EnumType.STRING)
    private DepartmentType departmentType;
    private String HOD;

    @ManyToOne
    @JoinColumn
    private Company company;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<Employee>employeeList=new ArrayList<>();








}
