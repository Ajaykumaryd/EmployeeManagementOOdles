package com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos;

import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
public class DeleteEmployeeFromDepartment {

    private Long did;
    private Long eid;


}
