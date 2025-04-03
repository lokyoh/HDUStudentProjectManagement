package com.lokyoh.hduspm.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAccount {
    @NotNull
    private String teacherId;
    @NotNull
    private String teacherName;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private String password;
}
