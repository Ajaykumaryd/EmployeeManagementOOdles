package com.Task1.OodlesA1.Dtos.RequestDto.ProjectDtos;

import lombok.Data;

import java.util.Date;

@Data
public class CreateProjectDto {

    private String projectName;

    private Date startingDate;

    private Date endDate;

}
