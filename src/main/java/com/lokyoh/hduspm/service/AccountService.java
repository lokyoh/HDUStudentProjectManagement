package com.lokyoh.hduspm.service;

import com.lokyoh.hduspm.entity.Account;
import com.lokyoh.hduspm.entity.StudentAccount;

public interface AccountService {
    Account getAccountByName(String username);

    void addStudent(StudentAccount student);

    boolean checkPassword(Long id, String p);
}
