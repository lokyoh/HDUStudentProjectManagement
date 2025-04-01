package com.lokyoh.hduspm.utils;

public class ValidateUtil {
    public static Boolean checkPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[a-z]).{8,20}$");
    }

    public static Boolean checkEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
