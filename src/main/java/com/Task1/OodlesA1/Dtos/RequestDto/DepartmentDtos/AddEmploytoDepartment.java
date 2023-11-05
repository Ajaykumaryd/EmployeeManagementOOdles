package com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos;

import com.Task1.OodlesA1.Enums.DepartmentType;
import lombok.Data;

@Data
public class AddEmploytoDepartment {

    private Long eid;

    private DepartmentType departmentType;


}
