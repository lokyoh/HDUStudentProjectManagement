package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.Account;
import com.lokyoh.hduspm.entity.Student;
import com.lokyoh.hduspm.entity.StudentAccount;

public interface UserService {
    Account getAccountByName(String username);

    void addStudent(StudentAccount student);

    boolean checkPassword(Long id, String p);

    Student sInfo(long id);

    void sChangeInfo(Student student);
}
