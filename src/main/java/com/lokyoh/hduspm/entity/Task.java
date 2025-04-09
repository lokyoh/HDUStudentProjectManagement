package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private Long projectId;
    private String projectName;
    private String name;
    private String description;
    private Long assignedTo;
    private String assigner;
    private LocalDate dueDate;
    private String status;
    private LocalDateTime createTime;
}
