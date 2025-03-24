package com.yjlearning.ProjectManagement.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCompletionDTO {
    private Integer projectId;
    private String message;
}