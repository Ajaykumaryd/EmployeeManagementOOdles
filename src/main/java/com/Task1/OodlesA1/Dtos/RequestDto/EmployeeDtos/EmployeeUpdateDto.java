package com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos;

import com.Task1.OodlesA1.Enums.Designation;
import com.Task1.OodlesA1.Enums.Gender;
import lombok.Data;

@Data
public class EmployeeUpdateDto {

    private Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    private Designation designation;


}
