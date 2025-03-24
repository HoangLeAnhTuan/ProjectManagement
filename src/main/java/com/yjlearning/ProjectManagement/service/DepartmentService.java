package com.yjlearning.ProjectManagement.service;

import java.util.List;

import com.yjlearning.ProjectManagement.entity.Department;

public interface DepartmentService {
    Department save(Department department);

    Department findById(Integer id);

    Department findByName(String name);

    List<Department> findAll();

}