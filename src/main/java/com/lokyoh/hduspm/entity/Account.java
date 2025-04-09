package com.lokyoh.hduspm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String role;
    private LocalDateTime createdAt;

    public Account(StudentAccount studentAccount) {
        this.username = studentAccount.getStudentId();
        this.password = studentAccount.getPassword();
        this.role = "student";
    }
    public Account(TeacherAccount teacherAccount) {
        this.username = teacherAccount.getTeacherId();
        this.password = teacherAccount.getPassword();
        this.role = "teacher";
    }
}
