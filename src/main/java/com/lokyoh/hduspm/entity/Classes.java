package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classes {
    private Long id;
    private String name;
    private String description;
    private String semester;
    private Long teacherId;
    private String teacherName;
    private LocalDate createdAt;
}
