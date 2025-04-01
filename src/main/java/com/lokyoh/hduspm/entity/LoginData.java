package com.lokyoh.hduspm.entity;

import lombok.Data;

@Data
public class LoginData {
    private Long id;
    private String username;
    private String role;
    private String token;

    public LoginData(Account account, String token) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.role = account.getRole();
        this.token = token;
    }
}
