package com.yjlearning.ProjectManagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yjlearning.ProjectManagement.dto.project.ProjectResponseDTO;
import com.yjlearning.ProjectManagement.entity.Project;

public interface ProjectService {
    Project save(Project project);

    Project findById(Integer id);

    Page<Project> findAll(Pageable pageable); // Keep only the pageable version

    void deleteById(Integer id);

    Project update(Project project);

    boolean existsByProjectId(Integer projectId);

    boolean existsByProjectName(String projectName);

    // Search-related methods
    Page<ProjectResponseDTO> getAllProjectsForDashboard(Pageable pageable);

    Page<ProjectResponseDTO> searchProjects(String searchField, String searchTerm, Pageable pageable);
}