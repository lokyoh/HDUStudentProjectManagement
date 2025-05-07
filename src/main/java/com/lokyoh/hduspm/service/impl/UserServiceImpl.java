package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.*;
import com.lokyoh.hduspm.mapper.UserMapper;
import com.lokyoh.hduspm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /** 获取用户账户信息
     * @param username 用户账户名称
     * @return 用户账户数据
     */
    @Override
    public Account getAccountByName(String username) {
        return userMapper.getAccountByName(username);
    }

    /** 添加学生
     * @param student 学生信息
     */
    @Override
    @Transactional
    public void addStudent(StudentAccount student) {
        userMapper.addAccount(new Account(student));
        userMapper.addStudent(new Student(student));
    }

    /**
     * @param id
     * @param p
     * @return
     */
    @Override
    public boolean checkPassword(Long id, String p) {
        return userMapper.getAccountById(id).getPassword().equals(p);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Student sInfo(long id) {
        return userMapper.studentInfo(id);
    }

    /**
     * @param student
     */
    @Override
    public void sChangeInfo(Student student) {
        userMapper.studentChangeInfo(student);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Teacher tInfo(Long id) {
        return userMapper.teacherInfo(id);
    }

    /**
     * @param teacher
     */
    @Override
    public void tChangeInfo(Teacher teacher) {
        userMapper.teacherChangeInfo(teacher);
    }

    /**
     * @param teacher
     */
    @Override
    @Transactional
    public void addTeacher(TeacherAccount teacher) {
        userMapper.addAccount(new Account(teacher));
        userMapper.addTeacher(new Teacher(teacher));
    }

    /**
     * @param id
     */
    @Override
    public void delAccount(Long id) {
        userMapper.delAccount(id);
    }

    /**
     * @param aid
     * @return
     */
    @Override
    public Object getUserInfo(Long aid) {
        Account account = userMapper.getAccountById(aid);
        return switch (account.getRole()) {
            case "student" -> userMapper.studentInfo(aid);
            case "teacher" -> userMapper.teacherInfo(aid);
            default -> null;
        };
    }

    /**
     * @param studentId
     * @return
     */
    @Override
    public Student getStudent(String studentId) {
        return userMapper.getStudentInfoByStudentId(studentId);
    }

    /**
     * @return
     */
    @Override
    public PageBean<Account> getUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageBean<Account> pb = new PageBean<>();
        List<Account> rs =userMapper.getUsers();
        PageInfo<Account> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }
}
