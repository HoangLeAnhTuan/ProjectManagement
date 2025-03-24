package com.yjlearning.ProjectManagement.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRegisterDTO {
    @NotNull(message = "Project ID is required")
    private Integer projectId;

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotNull(message = "Difficulty is required")
    @Pattern(regexp = "^[EMH]$", message = "Difficulty must be E, M, or H")
    private String difficulty;

    @NotNull(message = "Department is required")
    private Integer projectDept;
}