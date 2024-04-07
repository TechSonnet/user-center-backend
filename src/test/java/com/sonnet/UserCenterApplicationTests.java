package com.sonnet;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserCenterApplicationTests {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    void encryptPwd(){
        String pwd = "hello word";
        String encode = bCryptPasswordEncoder.encode(pwd);
        System.out.println(encode);
    }

    @Test
    void passwordPattern(){
        String passwordPattern = "^[a-zA-Z0-9]$";
        String password = "1234567890";
        boolean isValid = password.matches(passwordPattern);
        System.out.println(isValid); // true
        password = "admin";
        isValid = password.matches(passwordPattern);
        System.out.println(isValid); // false
    }

    @Test
    void huToolsTest(){
        boolean b = StrUtil.hasBlank("11", "9","12");
        System.out.println(b);
    }

    @Test
    void testHelloWord(){
        System.out.println("hello word");
        System.out.println("hello word");
        System.out.println("hello word");
        System.out.println("hello word");
        System.out.println("hello word");
        System.out.println("hello word");
    }

}
