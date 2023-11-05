package com.Task1.OodlesA1.Controller;

import com.Task1.OodlesA1.Constants.UrlMapping;
import com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos.AddEmploytoDepartment;
import com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos.DeleteEmployeeFromDepartment;
import com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos.DepartmentCreateDto;
import com.Task1.OodlesA1.Enums.DepartmentType;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeBelongsDifferentDepartment;
import com.Task1.OodlesA1.Exceptions.EmployeeIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeNotInDepartmentException;
import com.Task1.OodlesA1.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.DEPARTMENT)
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping()
    public String add(@RequestBody DepartmentCreateDto departmentCreateDto){
    String res=departmentService.add(departmentCreateDto);
    return res;
    }

    @PostMapping("/addEmp")
    public String addEmploy(@RequestBody AddEmploytoDepartment addEmploytoDepartment) throws EmployeeBelongsDifferentDepartment {
        try {
            String res=departmentService.addEmployee(addEmploytoDepartment);
            return res;
        }catch (EmployeeBelongsDifferentDepartment employeeBelongsDifferentDepartment){
            return employeeBelongsDifferentDepartment.getMessage();
        }
    }

    @DeleteMapping("/fromDepartment")
    public String removeEmployeeFromDepartment(@RequestParam Long eId, @RequestParam DepartmentType departmentType)throws EmployeeNotInDepartmentException,EmployeeIsNotPresent {
        try {
            String result = departmentService.removeEmployeeFromDepartment(eId, departmentType);
            return result;
        } catch (EmployeeNotInDepartmentException e) {
            return e.getMessage();
        }
    }
}
