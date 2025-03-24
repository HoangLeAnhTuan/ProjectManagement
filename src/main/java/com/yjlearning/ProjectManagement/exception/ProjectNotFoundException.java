package com.yjlearning.ProjectManagement.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Integer projectId) {
        super(String.format("Project not found with id: %d", projectId));
    }
}