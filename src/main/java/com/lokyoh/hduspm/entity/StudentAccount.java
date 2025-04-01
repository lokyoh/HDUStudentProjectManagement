package com.lokyoh.hduspm.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAccount {
    @NotNull
    private String studentId;
    @NotNull
    private String studentName;
    @NotNull
    private String classNumber;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private String password;
}
