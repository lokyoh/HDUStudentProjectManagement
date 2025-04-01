package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    Account getAccountByName(String username);
    void addAccount(Account account);
    Account getAccountById(Long id);
}
