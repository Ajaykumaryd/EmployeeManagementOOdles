package com.Task1.OodlesA1.Controller;
import com.Task1.OodlesA1.Constants.UrlMapping;
import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.AddDepartmentToCompany;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.AddEmployeeCompany;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyUpdateDto;
import com.Task1.OodlesA1.Dtos.ResponseDto.getEmployees;
import com.Task1.OodlesA1.Exceptions.*;
import com.Task1.OodlesA1.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.COMPANY)
public class CompanyController {

   @Autowired
   private CompanyService companyService;

    //API to Add Company
    @PostMapping()
    public String Company(@RequestBody CompanyCreateDto companyCreateDto){
    String res=companyService.add(companyCreateDto);
    return res;
    }

    //API to remove Company
    @DeleteMapping()
    public String delete(@RequestParam Integer cId){
    String result=companyService.deleted(cId);
    return result;
    }

    @PutMapping()
    public String update(@RequestBody CompanyUpdateDto companyUpdateDto) throws CompanyIsNotPresent {
    try {
     String res = companyService.change(companyUpdateDto);
     return res;
    }catch (CompanyIsNotPresent companyIsNotPresent){
     return companyIsNotPresent.getMessage();
    }
    }

    @GetMapping()
    public List<Company> getListOfCompanies(){
     List<Company>res=companyService.getList();
     return res;
    }

    @PostMapping("/add")
    public String add(@RequestBody AddEmployeeCompany addEmployeeCompany)throws CompanyIsNotPresent, EmployeeIsNotPresent, CompanySizeIsFull {
     try {
      String res=companyService.addemp(addEmployeeCompany);
      return res;
     }catch (EmployeeIsNotPresent | CompanyIsNotPresent | CompanySizeIsFull e){
      return e.getMessage();
     } catch (EmployeeIsAlreadyPresent e) {
         throw new RuntimeException(e);
     }
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@RequestBody AddDepartmentToCompany addDepartmentToCompany)throws CompanyIsNotPresent, DepartmentIsNotPresent {
    try {
     String res=companyService.addDepartmentCompany(addDepartmentToCompany);
     return res;
    }catch (CompanyIsNotPresent | DepartmentIsNotPresent e){
          return e.getMessage();
      }
    }
    @GetMapping("/male/{companyId}")
    public List<getEmployees> getMaleInCompany(@PathVariable Long companyId)throws CompanyIsNotPresent{

     List<getEmployees> getEmployeesList =companyService.getListMale(companyId);
     return getEmployeesList;
    }

 @GetMapping("/female/{companyId}")
 public List<getEmployees> getFeMaleInCompany(@PathVariable Long companyId)throws CompanyIsNotPresent{
  List<getEmployees> getEmployeesList =companyService.getListFemale(companyId);
  return getEmployeesList;
 }

}
