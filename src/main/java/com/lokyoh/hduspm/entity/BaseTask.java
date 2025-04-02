package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseTask {
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private Long assignedTo;
    private LocalDate dueDate;
    private String status;
    private LocalDateTime createdAt;
}
