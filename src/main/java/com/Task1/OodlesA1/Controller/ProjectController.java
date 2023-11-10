package com.Task1.OodlesA1.Controller;

import com.Task1.OodlesA1.Constants.UrlMapping;
import com.Task1.OodlesA1.Dtos.RequestDto.ProjectDtos.CreateProjectDto;
import com.Task1.OodlesA1.Exceptions.ProjectIsNotPresent;
import com.Task1.OodlesA1.Service.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.PROJECT)

public class ProjectController {


    @Autowired
    ProjectService projectService;

//    @PostMapping()
//    public String addProject(@RequestBody CreateProjectDto createProjectDto){
//     String res=projectService.addProject(createProjectDto);
//     return res;
//    }
//
//    @DeleteMapping()
//    public String delete(@RequestParam Long projectId) throws ProjectIsNotPresent {
//    String deelete=projectService.delete(projectId);
//    return deelete;
//    }





}
