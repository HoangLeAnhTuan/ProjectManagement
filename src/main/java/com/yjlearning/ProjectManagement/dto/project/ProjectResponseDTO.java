package com.yjlearning.ProjectManagement.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDTO {
    private Integer projectId;
    private String projectName;
    private Character difficulty;
    private String departmentName;
}