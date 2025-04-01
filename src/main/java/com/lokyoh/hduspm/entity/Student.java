package com.lokyoh.hduspm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private Long accountId;
    private String name;
    private String password;
    private String studentId;
    private String classNumber;
    private String email;
    private String phone;
    private String createdAt;

    public Student(StudentAccount studentAccount) {
        this.name = studentAccount.getStudentName();
        this.studentId = studentAccount.getStudentId();
        this.classNumber = studentAccount.getClassNumber();
        this.email = studentAccount.getEmail();
        this.phone = studentAccount.getPhone();
        this.password = studentAccount.getPassword();
    }
}
