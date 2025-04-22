package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInfo{
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
    private TaskInfo task;
    private ProjectStudent project;

    public ReviewInfo(Review review) {
        this.id = review.getId();
        this.taskId = review.getTaskId();
        this.taskName = review.getTaskName();
        this.projectId = review.getProjectId();
        this.projectName = review.getProjectName();
        this.auditorId = review.getAuditorId();
        this.status = review.getStatus();
        this.comments = review.getComments();
        this.createdAt = review.getCreatedAt();
        this.reviewedAt = review.getReviewedAt();
    }
}
