package com.sonnet.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sonnet.constant.UserConstant;
import com.sonnet.mapper.UserMapper;
import com.sonnet.model.domain.RegisterResult;
import com.sonnet.model.domain.User;
import com.sonnet.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author chang
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-01-16 20:18:12
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 处理用户注册
     *
     * @param userAccount
     * @param password
     * @param checkPassword
     * @return
     */
    @Override
    public long userRegister(String userAccount, String password, String checkPassword) {
        RegisterResult registerResult = new RegisterResult();

        // 1. 对参数进行校验
        int registerParameter = validateRegisterParameter(userAccount, password, checkPassword);
        if (registerParameter < 0){
//            registerResult.setStatus("error");
//            return registerResult;
            return -1;
        }
        // 2. 对密码进行加密
        String encryptPassword = Base64.encode(password);
        // 3，将数据存入数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
//            registerResult.setStatus("error");
//            return registerResult;
            return -1;
        }
//        registerResult.setStatus("ok");
//        return registerResult;
        return 1;
    }

    /**
     * 校验用户注册的参数
     *
     * @param userAccount
     * @param password
     * @param checkPassword
     * @return
     */
    private int validateRegisterParameter(String userAccount, String password, String checkPassword) {

        // 判断用户名、密码、二次密码是否为空
        boolean hasBlank = StrUtil.hasBlank(userAccount, password, checkPassword);
        if (hasBlank){
            return -1;
        }
        // 用户名长度大于 4 位
        if(userAccount.length() < 4){
            return -1;
        }
        // 密码长度必须大于 6 位
        if(password.length() < 6){
            return -1;
        }
        // 不包含特殊字符
        String userAccountPattern = "^[a-zA-Z0-9]+$";
        if(!userAccount.matches(userAccountPattern)){
            return -1;
        }
        // 密码和二次密码相等
        if(!password.equals(checkPassword)){
            return -1;
        }
        // 账号不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account", userAccount);
        Long count = userMapper.selectCount(userQueryWrapper);
        if (count > 0){
            return -1;
        }
        return 1;
    }


    /**
     * 处理用户注册
     *
     * @param userAccount
     * @param password
     * @param request
     * @return
     */

    @Override
    public User userLogin(String userAccount, String password, HttpServletRequest request) {
        // 参数校验
         if (!validateLoginParameter(userAccount,password)) {
            return null;
        }
        // 密码正确则返回用户信息，否则返回 null
        User user = isCorrectPassword(userAccount, password);
        if (user == null){
            return null;
        }
        // 用户信息脱敏
        User safeUser = hindUserInfo(user);
        // 保存用户状态
        saveUserStatus(safeUser, request);
        return safeUser;
    }

    /**
     * 通过用户名查询用户列表
     * @param username
     * @return
     */
    @Override
    public List<User> findByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("username", username);
        List<User> users = userMapper.selectList(userQueryWrapper);
        return users.stream().map(user -> hindUserInfo(user)).collect(Collectors.toList());
    }

    /**
     * 校验用户登录参数
     *
     * @param userAccount
     * @param password
     * @return
     */
    private boolean validateLoginParameter(String userAccount, String password) {
        if (StrUtil.hasBlank(userAccount,password)){
            return false;
        }
        return true;
    }

    /**
     * 用户登录密码是否正确，正确返回用户信息，否则返回 null
     * @param userAccount
     * @param password
     * @return
     */
    private User isCorrectPassword(String userAccount, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account", userAccount);
        userQueryWrapper.eq("user_password",Base64.encode(password));
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null){
            log.info("account or password is incorrect!");
            return null;
        }
        return user;
    }

    /**
     * 用户信息脱敏
     * @param user
     * @return
     */
    private User hindUserInfo(User user) {
        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUsername(user.getUsername());
        safeUser.setUserAccount(user.getUserAccount());
        safeUser.setAvatarUrl(user.getAvatarUrl());
        safeUser.setGender(user.getGender());
        safeUser.setPhone(user.getPhone());
        safeUser.setEmail(user.getEmail());
        safeUser.setUserStatus(user.getUserStatus());
        safeUser.setUserRole(0);
        return safeUser;
    }

    /**
     * 保存用户信息状态
     * @param user
     * @param request
     */
    private void saveUserStatus(User user, HttpServletRequest request) {
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
    }
}




