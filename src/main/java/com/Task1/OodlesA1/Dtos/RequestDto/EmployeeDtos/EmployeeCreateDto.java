package com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos;

import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Enums.DepartmentType;
import com.Task1.OodlesA1.Enums.Designation;
import com.Task1.OodlesA1.Enums.Gender;
import lombok.Data;

@Data
public class EmployeeCreateDto {

    private String name;
    private Integer age;
    private Gender gender;
    private String email;
    private Designation designation;
    private DepartmentType departmentType;
    private Long companyId;
    private Long departmentId;


}
