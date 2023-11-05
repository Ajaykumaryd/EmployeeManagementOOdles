package com.Task1.OodlesA1.Service;

import com.Task1.OodlesA1.Domain.Project;
import com.Task1.OodlesA1.Dtos.RequestDto.ProjectDtos.CreateProjectDto;
import com.Task1.OodlesA1.Repository.ProjectRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    ProjectRepository projectRepository;

    public String addProject(CreateProjectDto createProjectDto) {
    Project project=new Project();
    project.setProjectName(createProjectDto.getProjectName());
    project.setStartingDate(createProjectDto.getStartingDate());
    project.setEndDate(createProjectDto.getEndDate());
    projectRepository.save(project);
    return "Project Added Successfully";
    }
}
