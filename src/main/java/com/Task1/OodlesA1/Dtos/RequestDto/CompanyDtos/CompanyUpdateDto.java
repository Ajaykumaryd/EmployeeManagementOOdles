package com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateDto {

    private Long cid;

    private String companyName;

    private String location;

    private Integer noOfEmployees;

}

