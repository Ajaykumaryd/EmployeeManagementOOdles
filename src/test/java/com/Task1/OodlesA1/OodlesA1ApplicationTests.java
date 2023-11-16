package com.Task1.OodlesA1;

import com.Task1.OodlesA1.Controller.CompanyController;
import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyCreateDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;
import java.util.Optional;

@SpringBootTest(classes =OodlesA1Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OodlesA1ApplicationTests {

	@Autowired
	CompanyController companyController;
	@Test
	public void testAddCompany() {
		//mockito testing it is we are making dummy data of company and checking
		CompanyCreateDto companyCreateDto = new CompanyCreateDto();
		companyCreateDto.setCompanyName("Choco");
		companyCreateDto.setLocation("London");
		companyCreateDto.setNoOfEmployees(20);
		ResponseEntity<String> ans = companyController.addCompany(companyCreateDto);
		ResponseEntity<Company> companyResponse = companyController.getByname("Choco");
		if (Objects.nonNull(companyResponse)) {
			assertEquals(20, Objects.requireNonNull(companyResponse.getBody()).getNoOfEmployees());
		}else{
			assertEquals(20, 0);
			}
	     }
	}