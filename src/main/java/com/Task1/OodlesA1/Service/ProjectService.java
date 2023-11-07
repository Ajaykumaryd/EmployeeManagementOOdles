package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Domain.Project;
import com.Task1.OodlesA1.Dtos.RequestDto.ProjectDtos.CreateProjectDto;
import com.Task1.OodlesA1.Exceptions.ProjectIsNotPresent;
import com.Task1.OodlesA1.Repository.EmployeeRepository;
import com.Task1.OodlesA1.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;

    public String addProject(CreateProjectDto createProjectDto) {
    Project project=new Project();
    project.setProjectName(createProjectDto.getProjectName());
    project.setStartingDate(createProjectDto.getStartingDate());
    project.setEndDate(createProjectDto.getEndDate());
    if(createProjectDto.getEmployeeId()!=null){
        Optional<Employee>employee=employeeRepository.findById(Long.valueOf(createProjectDto.getEmployeeId()));
        if(employee.isPresent()){
            Employee employee1=employee.get();
            project.setEmployee(employee1);
        }
    }
    projectRepository.save(project);
    return "Project Added Successfully";
    }


    public String delete(Long projectId) throws ProjectIsNotPresent {
    Optional<Project>projectOptional=projectRepository.findById(projectId);
    if(projectOptional.isPresent()){
     projectRepository.delete(projectOptional.get());
    }else{
        throw new ProjectIsNotPresent("Project is Not Found");
    }
    return "Project deleted SucessFully";

    }
}
