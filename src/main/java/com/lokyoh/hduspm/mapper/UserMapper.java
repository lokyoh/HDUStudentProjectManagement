package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    Student getStudentInfoByStudentId(String studentId);

    List<Account> getUsers();

    List<ParamUser> getTeachers(String teacher);

    List<ParamUser> getStudents(String param);
}
