package com.yjlearning.ProjectManagement.service.impl;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjlearning.ProjectManagement.entity.Department;
import com.yjlearning.ProjectManagement.repository.DepartmentRepository;
import com.yjlearning.ProjectManagement.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    // @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department findById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByDeptName(name)
                .orElseThrow(() -> new RuntimeException("Department not found with name: " + name));
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
