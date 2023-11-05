package com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos;

import com.Task1.OodlesA1.Enums.DepartmentType;
import lombok.Data;

@Data
public class DepartmentCreateDto {


    private DepartmentType departmentType;

    private Long noOfEmployees;

    private String HOD;

}
