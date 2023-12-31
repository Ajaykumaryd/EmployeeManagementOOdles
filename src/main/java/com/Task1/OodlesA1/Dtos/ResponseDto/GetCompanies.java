package com.Task1.OodlesA1.Dtos.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCompanies {
    private String companyName;
    private String location;
    private Integer noOfEmployees;
}
