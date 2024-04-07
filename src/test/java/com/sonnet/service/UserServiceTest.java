package com.sonnet.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testUserRegister() {
        String userAccount = "dogyvpi";
        String password = "123456";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount, password, checkPassword);
        Assertions.assertTrue(result > 0);
    }
}