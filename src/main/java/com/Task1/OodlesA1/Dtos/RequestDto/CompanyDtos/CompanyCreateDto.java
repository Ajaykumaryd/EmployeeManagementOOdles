package com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos;

import lombok.Data;

@Data
public class CompanyCreateDto {

    private String companyName;
    private String location;
    private Integer noOfEmployees;

}
