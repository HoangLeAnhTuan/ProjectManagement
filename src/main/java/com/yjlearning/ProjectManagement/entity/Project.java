package com.yjlearning.ProjectManagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
    @Column(name = "PROJECT_ID")
    private Integer projectId;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "START_DAY")
    private LocalDateTime startDay;

    @Column(name = "END_DAY")
    private LocalDateTime endDay;

    @Column(name = "DIFFICULTY")
    private Character difficulty;

    @Column(name = "VERSION")
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "PROJECT_DEPT")
    private Department department;
}