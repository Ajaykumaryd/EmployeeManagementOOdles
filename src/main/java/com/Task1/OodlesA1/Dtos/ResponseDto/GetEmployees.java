package com.Task1.OodlesA1.Dtos.ResponseDto;

import com.Task1.OodlesA1.Enums.DepartmentType;
import com.Task1.OodlesA1.Enums.Designation;
import lombok.Data;

@Data
public class GetEmployees {

    private String empName;
    private DepartmentType departmentType;

    private Designation designation;



    private Integer age;

    private String email;



}
