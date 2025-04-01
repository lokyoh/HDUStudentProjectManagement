package com.lokyoh.hduspm.service.impl;

import com.lokyoh.hduspm.entity.Account;
import com.lokyoh.hduspm.entity.Student;
import com.lokyoh.hduspm.entity.StudentAccount;
import com.lokyoh.hduspm.mapper.AccountMapper;
import com.lokyoh.hduspm.mapper.StudentMapper;
import com.lokyoh.hduspm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private StudentMapper studentMapper;

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
}
