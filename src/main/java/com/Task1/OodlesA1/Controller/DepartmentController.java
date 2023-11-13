package com.Task1.OodlesA1.Controller;

import com.Task1.OodlesA1.Constants.UrlMapping;
import com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos.DepartmentCreateDto;
import com.Task1.OodlesA1.Exceptions.CompanyIsNotPresent;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Service.DepartmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.DEPARTMENT)

public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping()
    public String add(@RequestBody DepartmentCreateDto departmentCreateDto) throws CompanyIsNotPresent {
        try {
            String res = departmentService.add(departmentCreateDto);
            return res;
        } catch (CompanyIsNotPresent companyIsNotPresent) {
            return companyIsNotPresent.getMessage();
        }
    }

     @DeleteMapping()
     public String delete(@RequestParam Long departmentId) throws DepartmentIsNotPresent {
     String res=departmentService.delete(departmentId);
     return res;
     }



}
