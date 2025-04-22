package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskInfo {
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private Long assignedTo;
    private LocalDate dueDate;
    private String status;
    private String role;
    private LocalDateTime createdAt;
    private List<Member> members;
    private List<Review> reviews;
    private List<PFile> files;
    private TaskProgress progress;

    public TaskInfo(BaseTask task) {
        this.id = task.getId();
        this.projectId = task.getProjectId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.assignedTo = task.getAssignedTo();
        this.dueDate = task.getDueDate();
        this.status = task.getStatus();
        this.role = task.getRole();
        this.createdAt = task.getCreatedAt();
        this.members = new ArrayList<>();
        this.files = new ArrayList<>();
    }
}
