package com.Task1.OodlesA1;
import com.Task1.OodlesA1.Controller.CompanyController;
import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos.CompanyCreateDto;
import com.Task1.OodlesA1.Dtos.ResponseDto.GetCompanies;
import com.Task1.OodlesA1.Service.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ComponentScan(basePackages = "com.Task1.OodlesA1")
public class CompanyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;
    @InjectMocks  //controller will be accepting book repository as mock
    private CompanyController companyController;
    GetCompanies getCompanies = new GetCompanies("Parle", "Delhi", 2000);
    GetCompanies getCompanies1 = new GetCompanies("Adidas", "Mau", 4000);
    GetCompanies getGetCompanies2 = new GetCompanies("ParleG", "oty", 5000);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    //get list of companies
    @Test
    public void getCompanyTest() throws Exception {
        List<GetCompanies> companyList = new ArrayList<>(Arrays.asList(getCompanies, getCompanies1, getGetCompanies2));
        when(companyService.getList()).thenReturn(companyList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].companyName", is("ParleG")));
    }

//1.Mockito.when(...):Mockito.when(...) is part of the Mockito framework
// and is used to define the behavior of a mocked method.

//2.companyService.getList()companyService is a mock object of the CompanyService class.
// In testing, you often replace the actual implementation of a class with a mock object
// to control its behavior during testing.
//getList() is a method of the CompanyService class.
// The line is saying "when the getList() method is called on the companyService mock..."

//3..thenReturn(companyList):
//thenReturn(companyList) specifies what the method should return when it is called.
//In this case, it's saying "return the companyList when the getList() method is called.


    @Test
    public void testGetCompany() {
        // Mocking the behavior of the companyService
        Long companyId = 1L;
        GetCompanies getCompanies = new GetCompanies("Amazon", "Delhi", 200);
        Company company = new Company();
        company.setCompanyName(getCompanies.getCompanyName());
        company.setLocation(getCompanies.getLocation());
        company.setNoOfEmployees(getCompanies.getNoOfEmployees());
        when(companyService.get(anyLong())).thenReturn(company);

        // Calling the controller method
        ResponseEntity<Company> responseEntity = companyController.getCompany(companyId);
        // Verifying the response
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(company, responseEntity.getBody());
        assertEquals(company.getCompanyName(), Objects.requireNonNull(responseEntity.getBody()).getCompanyName());
        assertEquals(company.getCompanyName(), Objects.requireNonNull(responseEntity.getBody()).getCompanyName());
    }

    @Test
    public void testAddCompany() throws Exception {
        // Create a CompanyCreateDto for testing
        CompanyCreateDto companyCreateDto = new CompanyCreateDto("Amazon", "Delhi", 100);
        when(companyService.add(companyCreateDto)).thenReturn("Success");
        //Perform the Post request to the /api/v1/company endpoint

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyName\":\"Amazon\",\"location\":\"Delhi\",\"noOfEmployees\":100}"))
                        .andExpect(status().isAccepted());
         //Alternatively,you can call the addCompany method directly and asset the ResponseEntity
         ResponseEntity<String> responseEntity = companyController.addCompany(companyCreateDto);
         assert responseEntity.getStatusCode() == HttpStatus.ACCEPTED;
         assert Objects.equals(responseEntity.getBody(), "Success");
         assertEquals("Amazon", companyCreateDto.getCompanyName());
         assertEquals("Delhi", companyCreateDto.getLocation());
    }

    @Test
    public void testDeleteCompany() throws Exception {
        // Mock the behavior of companyService.deleted
        when(companyService.deleted(anyInt())).thenReturn("Deleted");

        // Perform the DELETE request to the /api/v1/company endpoint
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/company")
                        .param("cId", "1"))
                .andExpect(status().isAccepted());

        // Alternatively, you can call the delete method directly and assert the ResponseEntity
        ResponseEntity<String> responseEntity = companyController.delete(1);
        assertEquals(HttpStatus.ACCEPTED,responseEntity.getStatusCode());
        assert responseEntity.getStatusCode() == HttpStatus.ACCEPTED;
        assert Objects.equals(responseEntity.getBody(), "Deleted");
    }









}