package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyUpdateDto;
import com.Task1.OodlesA1.Dtos.ResponseDto.GetCompanies;
import com.Task1.OodlesA1.Exceptions.*;
import com.Task1.OodlesA1.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;


    public String add(CompanyCreateDto companyCreateDto){
        Company c = new Company();
        c.setCompanyName(companyCreateDto.getCompanyName());
        c.setLocation(companyCreateDto.getLocation());
        c.setNoOfEmployees(companyCreateDto.getNoOfEmployees());
        companyRepository.save(c);
        return "New Company Added";
    }


    public String deleted(Integer cId) throws CompanyIsNotPresent {
        Optional<Company> company = companyRepository.findById(Long.valueOf(cId));
        if (company.isPresent()) {
            companyRepository.deleteById(Long.valueOf(cId));
            return "Company Deleted with Id " + cId;
        } else {
            throw new CompanyIsNotPresent("Company is not present with Id " + cId);
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
     public List<GetCompanies> getList() {
     List<GetCompanies>getCompaniesList=new ArrayList<>();
     List<Company>companyList=companyRepository.findAll();
     for(Company company:companyList){
         GetCompanies getCompanies=new GetCompanies();
         getCompanies.setCompanyName(company.getCompanyName());
         getCompanies.setLocation(company.getLocation());
         getCompanies.setNoOfEmployees(company.getNoOfEmployees());
         getCompaniesList.add(getCompanies);
     }
     return getCompaniesList;
     }

    public Company get(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
            return company.get();
    }


    public Company getByname(String companyName) {
      Company company=companyRepository.findByCompanyName(companyName);
      return company;
    }
}