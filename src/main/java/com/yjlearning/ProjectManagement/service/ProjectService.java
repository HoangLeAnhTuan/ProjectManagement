package com.yjlearning.ProjectManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yjlearning.ProjectManagement.entity.Project;

public interface ProjectService {
    Project save(Project project);

    Project findById(Integer id);

    List<Project> findAll();

    Page<Project> findAll(Pageable pageable);

    void deleteById(Integer id);

    Project update(Project project);

    boolean existsByProjectId(Integer projectId);

    boolean existsByProjectName(String projectName);

    Page<Project> findByProjectId(Integer searchTerm, Pageable pageable);

    Page<Project> findByProjectName(String searchTerm, Pageable pageable);

    Page<Project> findByDifficulty(Character searchTerm, Pageable pageable);

    Page<Project> findByDepartmentName(String searchTerm, Pageable pageable);
}