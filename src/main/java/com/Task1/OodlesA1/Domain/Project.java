package com.Task1.OodlesA1.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="project")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectName;

    private Date startingDate;

    private Date endDate;

    @OneToOne
    @JoinColumn
    private Employee employee;





}
