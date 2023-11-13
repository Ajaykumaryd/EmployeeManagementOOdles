package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Domain.Department;
import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeUpdateDto;
import com.Task1.OodlesA1.Exceptions.CompanyIsNotPresent;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeException;
import com.Task1.OodlesA1.Repository.CompanyRepository;
import com.Task1.OodlesA1.Repository.DepartmentRepository;
import com.Task1.OodlesA1.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
   private EmployeeRepository employeeRepository;

    public String add(EmployeeCreateDto employeeCreateDto)throws  CompanyIsNotPresent,DepartmentIsNotPresent {
        Employee e=new Employee();
        e.setEmpName(employeeCreateDto.getName());
        e.setAge(employeeCreateDto.getAge());
        e.setEmail(employeeCreateDto.getEmail());
        e.setDesignation(employeeCreateDto.getDesignation());
        e.setGender(employeeCreateDto.getGender());
        e.setDepartmentType(employeeCreateDto.getDepartmentType());

        Optional<Department>departmentOptional=departmentRepository.findById(employeeCreateDto.getDepartmentId());
        if(departmentOptional.isEmpty()){
            throw new DepartmentIsNotPresent("Department not found with this ID "+employeeCreateDto.getDepartmentId());
        }
        Department department=departmentOptional.get();


        //we only save parent child get saved automatically
        Optional<Company>company=companyRepository.findById(employeeCreateDto.getCompanyId());
        Company company1=company.get();
        if(company.isPresent()){
            e.setCompany(company.get());
        }else{
            throw new CompanyIsNotPresent("Company not found");
        }

        e.setDepartment(department);
        e.setCompany(company.get());
//        e=employeeRepository.save(e);

        company1.getEmployeeList().add(e);
        department.getEmployeeList().add(e);
        companyRepository.save(company.get());
        departmentRepository.save(department);
        return "Employee has been Added";
    }


    public String deleteEmp(Integer empID) throws EmployeeException{
        Optional<Employee> existingEmployee = employeeRepository.findById(Long.valueOf(empID));
        if (existingEmployee.isPresent()) {
            employeeRepository.delete(existingEmployee.get());
            return "record deleted successfully";
        } else {
            throw new EmployeeException("Employee not Found with this Id");
        }
    }

    public List<Employee> getList(){
     return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getById(Integer eId) throws EmployeeException {
        Optional<Employee> employees = employeeRepository.findById(Long.valueOf(eId));
        if (employees.isEmpty()) {
            throw new EmployeeException("Employee is not present with Id");
        } else {
          return employees.get();
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
