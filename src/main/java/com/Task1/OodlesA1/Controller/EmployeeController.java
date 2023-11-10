package com.Task1.OodlesA1.Controller;

import com.Task1.OodlesA1.Constants.UrlMapping;
import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeUpdateDto;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeIsNotPresent;
import com.Task1.OodlesA1.Service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //creates a bean for us
@RequestMapping(UrlMapping.EMPLOYEE)
@SecurityRequirement(name ="employee")
public class EmployeeController {

    @Autowired  //Automatic Depedency Injection keeps the Consistency of data
    private EmployeeService employeeService;

    //API to add Employee
    @PostMapping()
    public String add(@RequestBody EmployeeCreateDto employeeCreateDto) throws DepartmentIsNotPresent {
    String result= employeeService.add(employeeCreateDto);
    return result;
    }

    @DeleteMapping()
    public String delete(@RequestParam Integer EId ) throws EmployeeIsNotPresent {

        try {
            String res= employeeService.deleteEmp(EId);
            return res;
        }catch (EmployeeIsNotPresent employeeIsNotPresent){
            return employeeIsNotPresent.getMessage();
        }
    }


    @GetMapping()
    public List<Employee> getallemployees(){
     List<Employee>list= employeeService.getList();
     return list;
    }

    //get employee details by id;
    @GetMapping("/{eid}")
    public Employee getById(@PathVariable Integer eid){
        Employee e= employeeService.getById(eid);
        return e;
    }

    @PutMapping()
    public String update(@RequestBody EmployeeUpdateDto employeeUpdateDto){
        String res= employeeService.change(employeeUpdateDto);
        return res;
    }


    @GetMapping("/greaterAge")
    public List<Employee> employeesGreater(@RequestParam Integer age){
    List<Employee>getEmployeesList=employeeService.getEmployees(age);
    return getEmployeesList;
    }



}
