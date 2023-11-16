package com.Task1.OodlesA1.Dtos.RequestDto.CompanyDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CompanyCreateDto {

    private String companyName;
    private String location;
    private Integer noOfEmployees;

}
