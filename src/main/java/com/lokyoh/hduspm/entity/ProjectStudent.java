package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectStudent {
    private Long id;
    private String name;
    private String description;
    private Long creatorId;
    private String creatorName;
    private Long teacherId;
    private String teacherName;
    private Long classId;
    private String className;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private String role;
}
