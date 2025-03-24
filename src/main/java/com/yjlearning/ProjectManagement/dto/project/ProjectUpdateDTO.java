package com.yjlearning.ProjectManagement.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Future;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateDTO {
    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotNull(message = "Difficulty is required")
    @Pattern(regexp = "^[EMH]$", message = "Difficulty must be E, M, or H")
    private String difficulty;

    @NotNull(message = "Department is required")
    private Integer projectDept;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDateTime endDay;

    @NotNull(message = "Version is required")
    private Integer version;
}