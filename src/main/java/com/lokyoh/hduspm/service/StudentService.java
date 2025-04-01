package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.Student;

public interface StudentService {
    Student info(long id);

    void changeInfo(Student student);
}
