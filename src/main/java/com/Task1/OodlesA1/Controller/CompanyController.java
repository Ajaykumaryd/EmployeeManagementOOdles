package com.Task1.OodlesA1.Controller;
import com.Task1.OodlesA1.Constants.UrlMapping;
import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyCreateDto;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyUpdateDto;
import com.Task1.OodlesA1.Exceptions.*;
import com.Task1.OodlesA1.Service.CompanyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.COMPANY)
@SecurityRequirement(name ="employee")
public class CompanyController {
   @Autowired
   private CompanyService companyService;

    //API to Add Company
    @PostMapping()
    public ResponseEntity<String> Company(@RequestBody CompanyCreateDto companyCreateDto){
    String res=companyService.add(companyCreateDto);
    return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    //API to remove Company
    @DeleteMapping()
    public ResponseEntity<String>  delete(@RequestParam Integer cId){
    String result=companyService.deleted(cId);
    return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }

    @PutMapping()
    public ResponseEntity<String>  update(@RequestBody CompanyUpdateDto companyUpdateDto) throws CompanyIsNotPresent {
    try {
     String res = companyService.change(companyUpdateDto);
     return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }catch (CompanyIsNotPresent companyIsNotPresent){
     return new ResponseEntity<>(companyIsNotPresent.getMessage(),HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping()
    public  ResponseEntity<List<Company>> getListOfCompanies(){
     List<Company>res=companyService.getList();
     return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }









}
