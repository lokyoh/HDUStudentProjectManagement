package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class STask {
    private Long id;
    private String name;
    private String description;
    private Long projectId;
    private String projectName;
    private Long assignedTo;
    private String assigner;
    private LocalDate dueDate;
    private String status;
    private String role;
    private LocalDate assignedAt;
    private Double progress;
    private String progressDescription;
}
