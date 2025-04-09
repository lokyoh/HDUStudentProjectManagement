package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.Account;
import com.lokyoh.hduspm.entity.Student;
import com.lokyoh.hduspm.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Account getAccountByName(String username);

    void addAccount(Account account);

    Account getAccountById(Long id);

    void addStudent(Student student);

    Student studentInfo(Long id);

    void studentChangeInfo(Student student);

    Teacher teacherInfo(Long id);

    void teacherChangeInfo(Teacher teacher);

    void addTeacher(Teacher teacher);

    void delAccount(Long id);

    Long getStudentId(Long id);
}
