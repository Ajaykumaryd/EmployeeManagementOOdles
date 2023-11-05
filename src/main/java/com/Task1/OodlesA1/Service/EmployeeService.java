package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeUpdateDto;
import com.Task1.OodlesA1.Dtos.ResponseDto.getEmployees;
import com.Task1.OodlesA1.Exceptions.EmployeeIsNotPresent;
import com.Task1.OodlesA1.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public String add(EmployeeCreateDto employeeCreateDto) {
        Employee E=new Employee();
        E.setEmpName(employeeCreateDto.getName());
        E.setAge(employeeCreateDto.getAge());
        E.setEmail(employeeCreateDto.getEmail());
        E.setDesignation(employeeCreateDto.getDesignation());
        E.setGender(employeeCreateDto.getGender());
        E.setDepartmentType(employeeCreateDto.getDepartmentType());
    employeeRepository.save(E);
    return "Employee has been Added";
    }


    public String deleteEmp(Integer empID) throws EmployeeIsNotPresent{

        Optional<Employee> existingEmployee = employeeRepository.findById(Long.valueOf(empID));
        if (existingEmployee.isPresent()) {
            employeeRepository.delete(existingEmployee.get());
            return "record deleted successfully";
        } else {
            throw new EmployeeIsNotPresent("Employee is not Present with this Id"+empID);
        }
    }

    public List<Employee> getList(){
     return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getById(Integer eId) {
        Optional<Employee>employees= employeeRepository.findById(Long.valueOf(eId));
        if(employees.isPresent()){
            return employees.get();
        }else{
            throw new RuntimeException("Employee not Found");
        }
    }

    public String  change(EmployeeUpdateDto employeeUpdateDto) {

        int id=employeeUpdateDto.getId();
        Employee employee= employeeRepository.findById((long) id).get();
        employee.setEmpName(employeeUpdateDto.getName());
        employee.setAge(employeeUpdateDto.getAge());
        employee.setDesignation(employeeUpdateDto.getDesignation());
        employee.setGender(employeeUpdateDto.getGender());
        employeeRepository.save(employee);
        return "change updated ";
        }


    public void changeAge(Integer eid, Integer newAge) {
        Optional<Employee>employees= employeeRepository.findById(Long.valueOf(eid));
        if(employees.isPresent()){
          Employee employee1 =employees.get();
          employee1.setAge(newAge);
        }else {
            throw new RuntimeException("Employee is not there");
        }
    }

    public String changeName(Integer id, String newName) {
        Optional<Employee>employees= employeeRepository.findById(Long.valueOf(id));
        if(employees.isPresent()){
           Employee employee=employees.get();
           employee.setEmpName(newName);
           return "name changed Successfully";
        }else{
            return "Employee is not present";
        }
    }

    public List<Employee> getEmployees(Integer age) {
     List<Employee>employeeList=employeeRepository.findEmployeeWithAgeGreater(age);
     return employeeList;
    }
}
