package com.lokyoh.hduspm.service.impl;

import com.lokyoh.hduspm.entity.*;
import com.lokyoh.hduspm.mapper.AccountMapper;
import com.lokyoh.hduspm.mapper.StudentMapper;
import com.lokyoh.hduspm.mapper.TeacherMapper;
import com.lokyoh.hduspm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /** 获取用户账户信息
     * @param username 用户账户名称
     * @return 用户账户数据
     */
    @Override
    public Account getAccountByName(String username) {
        return accountMapper.getAccountByName(username);
    }

    /** 添加学生
     * @param student 学生信息
     */
    @Override
    @Transactional
    public void addStudent(StudentAccount student) {
        accountMapper.addAccount(new Account(student));
        studentMapper.addStudent(new Student(student));
    }

    /**
     * @param id
     * @param p
     * @return
     */
    @Override
    public boolean checkPassword(Long id, String p) {
        return accountMapper.getAccountById(id).getPassword().equals(p);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Student sInfo(long id) {
        return studentMapper.info(id);
    }

    /**
     * @param student
     */
    @Override
    public void sChangeInfo(Student student) {
        studentMapper.changeInfo(student);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Teacher tInfo(Long id) {
        return teacherMapper.info(id);
    }

    /**
     * @param teacher
     */
    @Override
    public void tChangeInfo(Teacher teacher) {
        teacherMapper.changeInfo(teacher);
    }

    /**
     * @param teacher
     */
    @Override
    @Transactional
    public void addTeacher(TeacherAccount teacher) {
        accountMapper.addAccount(new Account(teacher));
        teacherMapper.add(new Teacher(teacher));
    }
}
