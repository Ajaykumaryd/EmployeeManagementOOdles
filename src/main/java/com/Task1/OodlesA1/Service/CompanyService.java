package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Domain.Department;
import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.AddDepartmentToCompany;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.AddEmployeeCompany;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyUpdateDto;
import com.Task1.OodlesA1.Dtos.ResponseDto.getEmployees;
import com.Task1.OodlesA1.Enums.Gender;
import com.Task1.OodlesA1.Exceptions.*;
import com.Task1.OodlesA1.Repository.CompanyRepository;
import com.Task1.OodlesA1.Repository.DepartmentRepository;
import com.Task1.OodlesA1.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;
    public String add(CompanyCreateDto companyCreateDto){
        Company c = new Company();
        c.setCompanyName(companyCreateDto.getCompanyName());
        c.setLocation(companyCreateDto.getLocation());
        c.setNoOfEmployees(companyCreateDto.getNoOfEmployees());
        companyRepository.save(c);
        return "New Company Added";
    }


    public String deleted(Integer cId) {
        Optional<Company> company = companyRepository.findById(Long.valueOf(cId));
        if (company.isPresent()) {
            companyRepository.deleteById(Long.valueOf(cId));
            return "Company deleted";
        } else {
            return "Company is not there";
        }
    }

    public String change(CompanyUpdateDto companyUpdateDto) throws CompanyIsNotPresent {
        Optional<Company> company = companyRepository.findById((long) Math.toIntExact(companyUpdateDto.getCid()));
        if (company.isPresent()) {
            Company company1 = company.get();
            company1.setCompanyName(company1.getCompanyName());
            company1.setLocation(company1.getLocation());
            company1.setLocation(company1.getLocation());
            company1.setNoOfEmployees(company1.getNoOfEmployees());
            companyRepository.save(company1);
            return "New Change Added";
        } else {
            throw new CompanyIsNotPresent("Company is not Present With given Id"+companyUpdateDto.getCid());
        }
    }

    public List<Company> getList() {
       List<Company> companyList=companyRepository.findAll();
       return companyList;
    }

    public String addemp(AddEmployeeCompany addEmployeeCompany) throws EmployeeIsNotPresent,CompanyIsNotPresent,CompanySizeIsFull, EmployeeIsAlreadyPresent {

    Optional<Employee> employeeOptional=employeeRepository.findById(addEmployeeCompany.getEmpId());
    if(employeeOptional.isEmpty()){
        throw new EmployeeIsNotPresent("Employee is Not Present with this EmployeeId"+addEmployeeCompany.getEmpId());
    }


   Optional<Company>companyOptional=companyRepository.findById(addEmployeeCompany.getComId());
    if(companyOptional.isEmpty()){
        throw new CompanyIsNotPresent("Company is Not Present with this comapnyId"+addEmployeeCompany.getComId());
    }
    int size=companyOptional.get().getNoOfEmployees();
    int curSize=companyOptional.get().getEmployeeList().size();

    if(size==curSize){
        throw new CompanySizeIsFull("Company is Full with Employees");
    }

    Employee employee=employeeOptional.get();
    Company company=companyOptional.get();
    //primaryKey Set
    employee.setCompany(company);
    company.getEmployeeList().add(employee);

    companyRepository.save(company);

    return "Employee added Successfully to Company";
    }

    public String addDepartmentCompany(AddDepartmentToCompany addDepartmentToCompany)throws CompanyIsNotPresent,DepartmentIsNotPresent {

        Optional<Company>companyOptional=companyRepository.findById(addDepartmentToCompany.getCompanyId());
        if(companyOptional.isEmpty()){
        throw new CompanyIsNotPresent("Company is Not Present");
        }

        Optional<Department>departmentOptional=departmentRepository.findById(addDepartmentToCompany.getDepartmentId());
        if(departmentOptional.isEmpty()) {
            throw new DepartmentIsNotPresent("Department is Not Present");
        }
        Company company=companyOptional.get();
        Department department=departmentOptional.get();

        //setting primary key
        department.setCompany(company);

        company.getDepartmentList().add(department);

        companyRepository.save(company);

        return "Company Added SuccessFully";

    }


    public List<getEmployees> getListMale(Long companyId)throws CompanyIsNotPresent {
     Optional<Company>companyOptional=companyRepository.findById(companyId);
     if(companyOptional.isEmpty()){
         throw new CompanyIsNotPresent("Company is Not Present");
     }
     Company company=companyOptional.get();
     List<Employee>employeeList=company.getEmployeeList();
     List<getEmployees> getEmployeesList =new ArrayList<>();
     for(Employee employee:employeeList){
         if(employee.getGender().equals(Gender.MALE)){
             getEmployees getEmployees =new getEmployees();
             getEmployees.setEmpName(employee.getEmpName());
             getEmployees.setAge(employee.getAge());
             getEmployees.setEmail(employee.getEmail());
             getEmployeesList.add(getEmployees);
         }
     }
     return getEmployeesList;
    }


    public List<getEmployees> getListFemale(Long companyId) throws CompanyIsNotPresent {
        Optional<Company>companyOptional=companyRepository.findById(companyId);
        if(companyOptional.isEmpty()){
            throw new CompanyIsNotPresent("Company is Not Present");
        }
        Company company=companyOptional.get();
        List<Employee>employeeList=company.getEmployeeList();
        List<getEmployees> getEmployeesList =new ArrayList<>();
        for(Employee employee:employeeList){
            if(employee.getGender().equals(Gender.FEMALE)){
                getEmployees getEmployees =new getEmployees();
                getEmployees.setEmpName(employee.getEmpName());
                getEmployees.setAge(employee.getAge());
                getEmployees.setEmail(employee.getEmail());
                getEmployeesList.add(getEmployees);
            }
        }
        return getEmployeesList;
    }
}
