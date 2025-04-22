package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskProgress {
    private Long id;
    private Long taskId;
    private Long studentId;
    private Double progress;
    private String description;
    private LocalDateTime updateAt;
}
