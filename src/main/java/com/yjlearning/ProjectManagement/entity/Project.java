package com.yjlearning.ProjectManagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Project")
public class Project {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    private String projectName;

    private LocalDateTime startDay;

    private LocalDateTime endDay;

    private Character difficulty;

    private Integer version;

    @ManyToOne
    @JoinColumn(name = "PROJECT_DEPT")
    private Department department;
}