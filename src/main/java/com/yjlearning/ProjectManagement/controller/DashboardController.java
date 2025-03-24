package com.yjlearning.ProjectManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yjlearning.ProjectManagement.dto.project.ProjectResponseDTO;
import com.yjlearning.ProjectManagement.service.ProjectService;

@Controller
public class DashboardController {

    private final ProjectService projectService;

    @Autowired
    public DashboardController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/dashboard")
    public String dashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String searchField,
            @RequestParam(required = false) String searchTerm,
            Model model) {

        Page<ProjectResponseDTO> projectPage;
        
        if (searchTerm != null && !searchTerm.trim().isEmpty() && searchField != null) {
            projectPage = projectService.searchProjects(searchField, searchTerm, PageRequest.of(page, size));
        } else {
            projectPage = projectService.getAllProjectsForDashboard(PageRequest.of(page, size));
        }

        model.addAttribute("projects", projectPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", projectPage.getTotalPages());
        model.addAttribute("totalItems", projectPage.getTotalElements());
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchTerm", searchTerm);

        return "dashboard";
    }
}