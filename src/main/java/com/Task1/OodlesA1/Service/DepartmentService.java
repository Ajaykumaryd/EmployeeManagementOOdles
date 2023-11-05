package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Department;
import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos.AddEmploytoDepartment;
import com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos.DeleteEmployeeFromDepartment;
import com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos.DepartmentCreateDto;
import com.Task1.OodlesA1.Enums.DepartmentType;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeBelongsDifferentDepartment;
import com.Task1.OodlesA1.Exceptions.EmployeeIsNotPresent;
import com.Task1.OodlesA1.Exceptions.EmployeeNotInDepartmentException;
import com.Task1.OodlesA1.Repository.DepartmentRepository;
import com.Task1.OodlesA1.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public String add(DepartmentCreateDto departmentCreateDto) {
        Department department = new Department();
        department.setDepartmentType(departmentCreateDto.getDepartmentType());
        department.setNoOFEmployees(departmentCreateDto.getNoOfEmployees());
        department.setHOD(departmentCreateDto.getHOD());
        departmentRepository.save(department);
        return "Department Added";
    }


    public String addEmployee(AddEmploytoDepartment addEmploytoDepartment) throws EmployeeBelongsDifferentDepartment {
        Optional<Employee> employee = employeeRepository.findById(addEmploytoDepartment.getEid());
        if (employee.isEmpty()) {
            throw new RuntimeException("Employee is not found");
        }

        if(!employee.get().getDepartmentType().equals(addEmploytoDepartment.getDepartmentType())){
            throw new EmployeeBelongsDifferentDepartment("Employee Belongs to different Department");
        }

        Optional<Department> departmentOptional = Optional.ofNullable((departmentRepository.findByDepartmentType(addEmploytoDepartment.getDepartmentType())));
        if (departmentOptional.isEmpty()) {
             return "Department not found";
        }
            Employee employee1 = employee.get();
            Department department = departmentOptional.get();

            //Setting Foreign Keys
            employee1.setDepartment(department);

            //Setting Bidirectional Mapping
            department.getEmployeeList().add(employee1);

            //Saving only Parent Entity,Child Will Save Automatically
            departmentRepository.save(department);
            return "department added successfully with employee";
        }


    public String removeEmployeeFromDepartment(Long eId, DepartmentType departmentType)throws EmployeeIsNotPresent,EmployeeNotInDepartmentException
    {
        Optional<Employee> employee = employeeRepository.findById(eId);
        if (employee.isEmpty()) {
            throw new EmployeeIsNotPresent("Employee is not Present");
        }

        Department department = departmentRepository.findByDepartmentType(departmentType);

        if (!employee.get().getDepartmentType().equals(departmentType)) {
            throw new EmployeeNotInDepartmentException("Employee does not belong to this department");
        }

        // Remove the employee from the department
        department.getEmployeeList().remove(employee.get());
        employee.get().setDepartment(null);

        departmentRepository.save(department);
        return "Employee removed from the department successfully";
    }
}
