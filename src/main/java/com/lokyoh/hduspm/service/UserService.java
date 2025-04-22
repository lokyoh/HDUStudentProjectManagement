package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.*;

public interface UserService {
    Account getAccountByName(String username);

    void addStudent(StudentAccount student);

    boolean checkPassword(Long id, String p);

    Student sInfo(long id);

    void sChangeInfo(Student student);

    Teacher tInfo(Long id);

    void tChangeInfo(Teacher teacher);

    void addTeacher(TeacherAccount teacher);

    void delAccount(Long id);

    Object getUserInfo(Long aid);

    Student getStudent(String studentId);
}
