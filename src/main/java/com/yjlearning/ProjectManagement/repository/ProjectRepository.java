package com.yjlearning.ProjectManagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yjlearning.ProjectManagement.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    boolean existsByProjectId(Integer projectId);

    boolean existsByProjectName(String projectName);

    @Query("SELECT p FROM Project p WHERE p.projectId = :searchTerm")
    Page<Project> findByProjectId(@Param("searchTerm") Integer searchTerm, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE LOWER(p.projectName) LIKE LOWER(CONCAT('%',:searchTerm,'%'))")
    Page<Project> findByProjectNameContainingIgnoreCase(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE LOWER(p.difficulty) = LOWER(:searchTerm)")
    Page<Project> findByDifficulty(@Param("searchTerm") Character searchTerm, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE LOWER(p.department.deptName) LIKE LOWER(CONCAT('%', :searchTerm,'%'))")
    Page<Project> findByDepartmentNameContainingIgnoreCase(@Param("searchTerm") String searchTerm, Pageable pageable);

}
