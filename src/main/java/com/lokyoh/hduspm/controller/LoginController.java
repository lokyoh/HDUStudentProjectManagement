package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.*;
import com.lokyoh.hduspm.service.AccountService;
import com.lokyoh.hduspm.utils.JwtUtil;
import com.lokyoh.hduspm.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@Component
public class LoginController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public Result<Object> login(String username, String password) {
        Account account = accountService.getAccountByName(username);
        if (account == null) {
            return Result.error("用户名错误");
        }
        if (account.getPassword().equals(password)) {
            String role = account.getRole();
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", account.getId());
            claims.put("role", role);
            String token = JwtUtil.getToken(claims);
            return Result.success(new LoginData(account, token));
        } else {
            return Result.error("密码错误");
        }
    }

    @PutMapping("/register")
    public Result<Object> register(@RequestBody StudentAccount student) {
        Account account = accountService.getAccountByName(String.valueOf(student.getStudentId()));
        if (account != null)
            return Result.error("该账号已经注册");
        if (!ValidateUtil.checkPassword(student.getPassword()))
            return Result.error("密码不符合要求");
        if (!ValidateUtil.checkEmail(student.getEmail()))
            return Result.error("邮箱不符合要求");
        accountService.addStudent(student);
        return Result.success();
    }

    @PostMapping("/tokenCheck")
    public Result<Object> tokenCheck(String token) {
        try{
            JwtUtil.parseToken(token);
            return Result.success();
        }catch (Exception e){
            return Result.error("token无效");
        }
    }
}
