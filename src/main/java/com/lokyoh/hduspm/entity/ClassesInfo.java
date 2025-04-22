package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassesInfo {
    private Long id;
    private String name;
    private String description;
    private String semester;
    private Long teacherId;
    private String teacherName;
    private LocalDate createdAt;
    private String role;
    private List<BaseProject> projects;
    private List<Member> members;

    public ClassesInfo(Classes classes) {
        this.id = classes.getId();
        this.name = classes.getName();
        this.description = classes.getDescription();
        this.semester = classes.getSemester();
        this.teacherId = classes.getTeacherId();
        this.teacherName = classes.getTeacherName();
        this.createdAt = classes.getCreatedAt();
    }
}
