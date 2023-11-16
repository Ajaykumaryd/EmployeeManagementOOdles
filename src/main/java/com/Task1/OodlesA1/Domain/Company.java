package com.Task1.OodlesA1.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="company")
@Data

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;

    private String companyName;

    private String location;

    private Integer noOfEmployees;

    @JsonIgnore
    @OneToMany(mappedBy = "company",cascade =CascadeType.ALL)
    List<Department>departmentList=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    List<Employee>employeeList=new ArrayList<>();


}
