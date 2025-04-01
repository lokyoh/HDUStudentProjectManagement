package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SProject {
    private Long id;
    private String name;
    private String description;
    private String creatorId;
    private String creatorName;
    private Long classId;
    private String className;
    private String status;
    private String reviewStatus;
    private String startDate;
    private String endDate;
    private String createdAt;
    private String role;
}
