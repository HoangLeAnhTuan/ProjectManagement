package com.yjlearning.ProjectManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yjlearning.ProjectManagement.entity.Project;
import com.yjlearning.ProjectManagement.repository.ProjectRepository;
import com.yjlearning.ProjectManagement.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    // @Autowired
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
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        projectRepository.deleteById(id);
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

    @Override
    public Page<Project> findByProjectId(Integer searchTerm, Pageable pageable) {
        return projectRepository.findByProjectId(searchTerm, pageable);
    }

    @Override
    public Page<Project> findByProjectName(String searchTerm, Pageable pageable) {
        return projectRepository.findByProjectNameContainingIgnoreCase(searchTerm, pageable);
    }

    @Override
    public Page<Project> findByDifficulty(Character searchTerm, Pageable pageable) {
        return projectRepository.findByDifficulty(searchTerm, pageable);
    }

    @Override
    public Page<Project> findByDepartmentName(String searchTerm, Pageable pageable) {
        return projectRepository.findByDepartmentNameContainingIgnoreCase(searchTerm, pageable);
    }
}
