package com.Task1.OodlesA1.Controller;
import com.Task1.OodlesA1.Constants.UrlMapping;
import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyUpdateDto;
import com.Task1.OodlesA1.Dtos.ResponseDto.GetCompanies;
import com.Task1.OodlesA1.Exceptions.*;
import com.Task1.OodlesA1.Service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.COMPANY)
public class CompanyController {
   @Autowired
   private CompanyService companyService;

    //API to Add Company

    @PostMapping()
    public ResponseEntity<String> addCompany(@RequestBody CompanyCreateDto companyCreateDto){
    String res=companyService.add(companyCreateDto);
    return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    //API to remove Company
    @DeleteMapping()
    public ResponseEntity<String>  delete(@RequestParam Integer cId) throws CompanyIsNotPresent {
    String result=companyService.deleted(cId);
    return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }

     @PutMapping()
     public ResponseEntity<String>  update(@RequestBody CompanyUpdateDto companyUpdateDto) throws CompanyIsNotPresent {
      try {
      String res = companyService.change(companyUpdateDto);
      return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
      }
      catch (CompanyIsNotPresent companyIsNotPresent){
      return new ResponseEntity<>(companyIsNotPresent.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }

     @GetMapping()
     public  ResponseEntity<List<GetCompanies>> getListOfCompanies(){
     List<GetCompanies> res=companyService.getList();
     return new ResponseEntity<>(res,HttpStatus.OK);
     }

     @GetMapping("/byName")
     public ResponseEntity<Company>getByname(String companyName){
     Company company=companyService.getByname(companyName);
     return new ResponseEntity<>(company,HttpStatus.OK);
     }

     @GetMapping("/get")
     public  ResponseEntity<Company> getCompany(@RequestParam Long companyId){
     Company company=companyService.get(companyId);
     return new ResponseEntity<>(company,HttpStatus.OK);
     }









}
