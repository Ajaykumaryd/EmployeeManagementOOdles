package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Domain.Department;
import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.EmployeeDtos.EmployeeUpdateDto;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeIsNotPresent;
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

    public String add(EmployeeCreateDto employeeCreateDto) throws DepartmentIsNotPresent
    {
        Employee e=new Employee();
        e.setEmpName(employeeCreateDto.getName());
        e.setAge(employeeCreateDto.getAge());
        e.setEmail(employeeCreateDto.getEmail());
        e.setDesignation(employeeCreateDto.getDesignation());
        e.setGender(employeeCreateDto.getGender());
        e.setDepartmentType(employeeCreateDto.getDepartmentType());
        if(employeeCreateDto.getCompanyId()!=null){
            Optional<Company>companyOptional=companyRepository.findById(employeeCreateDto.getCompanyId());
            if(companyOptional.isPresent()){
                Company company=companyOptional.get();
              e.setCompany(company);
            }
        }
     if(employeeCreateDto.getDepartmentId()==null) {
         throw new DepartmentIsNotPresent("Department is not found");
     }
        Optional<Department>department=departmentRepository.findById(employeeCreateDto.getDepartmentId());
        if(department.isPresent()){
            e.setDepartment(department.get());
        }
        department.get().getEmployeeList().add(e);

    //we only save parent child saved automatically
    departmentRepository.save(department.get());


    employeeRepository.save(e);
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
