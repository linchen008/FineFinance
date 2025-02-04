package com.itheima.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestAll {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPwd() {
        String pwd = "123456";
        //加密  $2a$10$WAWV.QEykot8sHQi6FqqDOAnevkluOZJqZJ5YPxSnVVWqvuhx88Ha
        String encode = passwordEncoder.encode(pwd);
        System.out.println(encode);
    }

    @Test
    public void testPwdMatch() {
        /*
            matches()匹配明文密码和加密后密码是否匹配，如果匹配，返回true，否则false
            just test
         */
        String pwd = "123456";
        boolean flag = passwordEncoder.matches(pwd, "$2a$10$5Z4r8Rjd7whpXoq/DVyH0u9b7mZ.QR3oMY89UbM9X51hqRwnUw6zy");
        System.out.println(flag);
    }
}    