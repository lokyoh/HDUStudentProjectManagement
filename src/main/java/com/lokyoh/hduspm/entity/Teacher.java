package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Long id;
    private Long accountId;
    private String name;
    private String password;
    private String teacherId;
    private String email;
    private String phone;
    private String createdAt;

    public Teacher(TeacherAccount teacherAccount) {
        this.name = teacherAccount.getTeacherName();
        this.teacherId = teacherAccount.getTeacherId();
        this.email = teacherAccount.getEmail();
        this.phone = teacherAccount.getPhone();
        this.password = teacherAccount.getPassword();
    }
}
