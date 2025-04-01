package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.Student;
import com.lokyoh.hduspm.entity.StudentAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    void addStudent(Student student);

    Student info(long id);

    void changeInfo(Student student);
}
