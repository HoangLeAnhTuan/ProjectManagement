package com.yjlearning.ProjectManagement.mapper;

import org.springframework.stereotype.Component;
import com.yjlearning.ProjectManagement.dto.project.ProjectResponseDTO;
import com.yjlearning.ProjectManagement.entity.Project;

@Component
public class ProjectMapper {
    
    public ProjectResponseDTO toResponseDTO(Project project) {
        return new ProjectResponseDTO(
            project.getProjectId(),
            project.getProjectName(),
            project.getDifficulty(),
            project.getDepartment().getDeptName()
        );
    }
}