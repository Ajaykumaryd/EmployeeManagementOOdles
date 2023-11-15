package com.Task1.OodlesA1.Controller;

import com.Task1.OodlesA1.Constants.UrlMapping;
import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeUpdateDto;
import com.Task1.OodlesA1.Dtos.ResponseDto.GetEmployees;
import com.Task1.OodlesA1.Exceptions.CompanyIsNotPresent;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeException;
import com.Task1.OodlesA1.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //creates a bean for us
@RequestMapping(UrlMapping.EMPLOYEE)
public class EmployeeController {

    @Autowired  //Automatic Depedency Injection keeps the Consistency of data
    private EmployeeService employeeService;

    //API to add Employee
    @PostMapping()
    public ResponseEntity<String> add(@RequestBody EmployeeCreateDto employeeCreateDto) throws DepartmentIsNotPresent,CompanyIsNotPresent {
        try {
            String result = employeeService.add(employeeCreateDto);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (DepartmentIsNotPresent | CompanyIsNotPresent ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Integer EId ) {
        try {
            String res = employeeService.deleteEmp(EId);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        } catch (EmployeeException employeeException) {
            return new ResponseEntity<>(employeeException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




    @GetMapping()
    public ResponseEntity<List<GetEmployees>> getallemployees(@RequestParam Long companyId){
     List<GetEmployees>getEmployeesList=employeeService.getList(companyId);
     return new ResponseEntity<>(getEmployeesList,HttpStatus.ACCEPTED);
    }

    //get employee details by id;
    @GetMapping("/{eid}")
    public ResponseEntity<Employee> getById(@PathVariable Integer eid) {
        try {
            Employee e = employeeService.getById(eid);
            return new ResponseEntity<>(e,HttpStatus.ACCEPTED);
        } catch (EmployeeException e) {
            throw new EmployeeException("Employee is not found this Id " + eid);
        }
    }
    @PutMapping()
    public String update(@RequestBody EmployeeUpdateDto employeeUpdateDto){
        String res= employeeService.change(employeeUpdateDto);
        return res;
    }


//    @GetMapping("/greaterAge")
//    public List<Employee> employeesGreater(@RequestParam Integer age){
//    List<Employee>getEmployeesList=employeeService.getEmployees(age);
//    return getEmployeesList;
//    }


}
