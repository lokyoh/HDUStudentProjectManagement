package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Long id;
    private String name;
    private String description;
    private Long creatorId;
    private String creatorName;
    private Long classId;
    private String className;
    private String status;
    private String reviewStatus;
    private LocalDate startDate;
    private LocalDate endDate;
}
