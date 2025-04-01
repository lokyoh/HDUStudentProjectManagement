package com.lokyoh.hduspm;

import com.lokyoh.hduspm.utils.ValidateUtil;
import org.junit.jupiter.api.Test;

public class ValidateUtilTest {
    @Test
    public void testValidate() {
        System.out.println(ValidateUtil.checkEmail("abc@gmail.com"));
        System.out.println(ValidateUtil.checkEmail("abc@.com"));
    }
}
