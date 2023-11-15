package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Domain.Department;
import com.Task1.OodlesA1.Dtos.RequestDto.DepartmentDtos.DepartmentCreateDto;
import com.Task1.OodlesA1.Exceptions.CompanyIsNotPresent;
import com.Task1.OodlesA1.Exceptions.DepartmentIsNotPresent;
import com.Task1.OodlesA1.Repository.CompanyRepository;
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
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public String add(DepartmentCreateDto departmentCreateDto) throws CompanyIsNotPresent {
        Department department = new Department();
        department.setDepartmentType(departmentCreateDto.getDepartmentType());
        department.setNoOFEmployees(departmentCreateDto.getNoOfEmployees());
        department.setHod(departmentCreateDto.getHod());
        if(departmentCreateDto.getCompanyId()!=null){
            Optional<Company>companyOptional=companyRepository.findById(departmentCreateDto.getCompanyId());
            if(companyOptional.isPresent()){
               Company company=companyOptional.get();

               //set foreign key
               department.setCompany(company);

               //bidirectional mapping
               company.getDepartmentList().add(department);

               //we only save company department get automatically saved
               companyRepository.save(company);
            }
        }

        return "Department Added";
    }


    public String delete(Long departmentId) throws DepartmentIsNotPresent {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isPresent()) {
            departmentRepository.delete(departmentOptional.get());
            return "department deleted with Id "+departmentId;
        } else {
            throw new DepartmentIsNotPresent("department is not Present with Id " + departmentId);
        }
    }

}
