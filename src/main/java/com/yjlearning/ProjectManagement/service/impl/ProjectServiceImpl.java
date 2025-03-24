package com.yjlearning.ProjectManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yjlearning.ProjectManagement.entity.Project;
import com.yjlearning.ProjectManagement.exception.ProjectNotFoundException;
import com.yjlearning.ProjectManagement.repository.ProjectRepository;
import com.yjlearning.ProjectManagement.service.ProjectService;
import com.yjlearning.ProjectManagement.dto.project.ProjectResponseDTO;;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project findById(Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @Override
    public void deleteById(Integer id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }
        projectRepository.deleteById(id);
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Project update(Project project) {
        if (!projectRepository.existsById(project.getProjectId())) {
            throw new RuntimeException("Project not found with id: " + project.getProjectId());
        }
        return projectRepository.save(project);
    }

    @Override
    public boolean existsByProjectId(Integer projectId) {
        return projectRepository.existsByProjectId(projectId);
    }

    @Override
    public boolean existsByProjectName(String projectName) {
        return projectRepository.existsByProjectName(projectName);
    }

    // Remove these individual search methods as they're handled by searchProjects
    // findByProjectId
    // findByProjectName
    // findByDifficulty
    // findByDepartmentName

    @Override
    public Page<ProjectResponseDTO> getAllProjectsForDashboard(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return projects.map(this::convertToDTO);
    }

    private ProjectResponseDTO convertToDTO(Project project) {
        return new ProjectResponseDTO(
                project.getProjectId(),
                project.getProjectName(),
                project.getDifficulty(),
                project.getDepartment() != null ? project.getDepartment().getDeptName() : "No Department");
    }

    @Override
    public Page<ProjectResponseDTO> searchProjects(String searchField, String searchTerm, Pageable pageable) {
        if (searchTerm == null || searchTerm.trim().isEmpty() || searchField == null) {
            return getAllProjectsForDashboard(pageable);
        }

        Page<Project> projectPage;
        searchTerm = searchTerm.trim();

        switch (searchField) {
            case "projectId":
                try {
                    Integer id = Integer.parseInt(searchTerm);
                    projectPage = projectRepository.findByProjectId(id, pageable);
                } catch (NumberFormatException e) {
                    return Page.empty(pageable);
                }
                break;

            case "projectName":
                projectPage = projectRepository.findByProjectNameContainingIgnoreCase(searchTerm, pageable);
                break;

            case "difficulty":
                if (searchTerm.length() == 1 && "EMH".contains(searchTerm.toUpperCase())) {
                    projectPage = projectRepository.findByDifficulty(searchTerm.toUpperCase().charAt(0), pageable);
                } else {
                    return Page.empty(pageable);
                }
                break;

            case "departmentName":
                projectPage = projectRepository.findByDepartmentNameContainingIgnoreCase(searchTerm, pageable);
                break;

            default:
                return getAllProjectsForDashboard(pageable);
        }

        return projectPage.map(this::convertToDTO);
    }
}
