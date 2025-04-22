package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Long id;
    private Long taskId;
    private String taskName;
    private Long projectId;
    private String projectName;
    private Long auditorId;
    private String status;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime reviewedAt;
}
