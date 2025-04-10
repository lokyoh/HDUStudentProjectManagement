package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PFile {
    public Long id;
    public Long projectId;
    public Long taskId;
    public Long studentId;
    public String studentName;
    public String fileName;
    public Long fileSize;
    public LocalDateTime uploadTo;
    public String description;
}
