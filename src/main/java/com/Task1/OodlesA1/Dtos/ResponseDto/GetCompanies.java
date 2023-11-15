package com.Task1.OodlesA1.Dtos.ResponseDto;

import lombok.Data;

@Data
public class GetCompanies {
    private String companyName;
    private String location;
    private Integer noOfEmployees;
}
