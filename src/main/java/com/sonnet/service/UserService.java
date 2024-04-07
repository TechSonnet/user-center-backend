package com.sonnet.service;

import com.sonnet.model.domain.RegisterResult;
import com.sonnet.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author chang
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-01-16 20:18:12
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount, String password, String checkPassword);

    User userLogin(String userAccount, String password, HttpServletRequest request);

    List<User> findByUsername(String username);
}
