package com.yjlearning.ProjectManagement.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjlearning.ProjectManagement.dto.project.DeleteCompletionDTO;
import com.yjlearning.ProjectManagement.dto.project.ProjectRegisterDTO;
import com.yjlearning.ProjectManagement.dto.project.ProjectResponseDTO;
import com.yjlearning.ProjectManagement.dto.project.ProjectUpdateDTO;
import com.yjlearning.ProjectManagement.entity.Department;
import com.yjlearning.ProjectManagement.entity.Project;
import com.yjlearning.ProjectManagement.mapper.ProjectMapper;
import com.yjlearning.ProjectManagement.service.DepartmentService;
import com.yjlearning.ProjectManagement.service.ProjectService;

@Controller
public class ProjectController {
    private final ProjectService projectService;
    private final DepartmentService departmentService;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectController(ProjectService projectService, 
                           DepartmentService departmentService,
                           ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.departmentService = departmentService;
        this.projectMapper = projectMapper;
    }

    @PostMapping("/project/delete")
    public String deleteProject(@RequestParam Integer projectId, Model model) {
        projectService.deleteById(projectId);

        DeleteCompletionDTO completion = new DeleteCompletionDTO(
                projectId,
                String.format("Delete complete for project with ID #%d", projectId));

        model.addAttribute("completion", completion);
        return "delete-complete";
    }

    // Add these new methods to your existing ProjectController

    @GetMapping("/project/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "project-register";
    }

    @PostMapping("/project/register")
    @ResponseBody
    public ResponseEntity<?> registerProject(@RequestBody @Validated ProjectRegisterDTO projectDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            Map<String, Object> response = new HashMap<>();
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        if (projectService.existsByProjectId(projectDTO.getProjectId())) {
            Map<String, Object> response = new HashMap<>();
            Map<String, String> errors = new HashMap<>();
            errors.put("projectId", "Project ID already exists");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Project project = new Project();
        project.setProjectId(projectDTO.getProjectId());
        project.setProjectName(projectDTO.getProjectName());
        project.setDifficulty(projectDTO.getDifficulty().charAt(0));
        project.setVersion(1);
        project.setStartDay(LocalDateTime.now());
        project.setEndDay(LocalDateTime.now().plusMonths(6));

        Department dept = departmentService.findById(projectDTO.getProjectDept());
        project.setDepartment(dept);

        Project savedProject = projectService.save(project);
        return ResponseEntity.ok(new ProjectResponseDTO(
                savedProject.getProjectId(),
                savedProject.getProjectName(),
                savedProject.getDifficulty(),
                savedProject.getDepartment().getDeptName()));
    }

    @GetMapping("/project/register/complete")
    public String registerComplete(@RequestParam Integer projectId, Model model) {
        Project project = projectService.findById(projectId);
        model.addAttribute("completion", new DeleteCompletionDTO(
                projectId,
                String.format("Register complete for %s with ID #%d",
                        project.getProjectName(), project.getProjectId())));
        return "delete-complete";
    }

    // Add these methods to your existing ProjectController class

    @GetMapping("/project/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer projectId, Model model) {
        Project project = projectService.findById(projectId);
        if (project == null) {
            throw new RuntimeException("Project not found with id: " + projectId);
        }

        model.addAttribute("projectId", projectId);
        model.addAttribute("project", project);
        model.addAttribute("departments", departmentService.findAll());

        return "project-update";
    }

    @PostMapping("/project/update/{id}")
    @ResponseBody
    public ResponseEntity<?> updateProject(
            @PathVariable("id") Integer projectId,
            @RequestBody @Validated ProjectUpdateDTO projectDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                        FieldError::getField, 
                        FieldError::getDefaultMessage,
                        (first, second) -> first
                    ));
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        Project project = projectService.findById(projectId);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        updateProjectFields(project, projectDTO);
        Project updatedProject = projectService.save(project);
        
        return ResponseEntity.ok(projectMapper.toResponseDTO(updatedProject));
    }

    private void updateProjectFields(Project project, ProjectUpdateDTO dto) {
        project.setProjectName(dto.getProjectName());
        project.setDifficulty(dto.getDifficulty().charAt(0));
        project.setDepartment(departmentService.findById(dto.getProjectDept()));
        project.setEndDay(dto.getEndDay());
        project.setVersion(project.getVersion() + 1);
    }
}