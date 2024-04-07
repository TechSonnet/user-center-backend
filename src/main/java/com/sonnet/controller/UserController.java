package com.sonnet.controller;

import cn.hutool.core.util.StrUtil;
import com.sonnet.model.domain.RegisterResult;
import com.sonnet.model.domain.User;
import com.sonnet.model.request.UserLoginRequest;
import com.sonnet.model.request.UserRegisterRequest;
import com.sonnet.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.sonnet.constant.UserConstant.ADMIN_ROLE;
import static com.sonnet.constant.UserConstant.USER_LOGIN_STATE;


/**
 * 用户接口
 *
 * @author techsonnet
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册接口
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
//        RegisterResult registerResult = new RegisterResult();

        if (userRegisterRequest == null){
//            registerResult.setStatus("error");
//            return registerResult;
            return -1;
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        if(StrUtil.hasBlank(userAccount,password,checkPassword)){
//            registerResult.setStatus("error");
//            return registerResult;
            return -1;
        }
        return userService.userRegister(userAccount,password,checkPassword);
    }


    /**
     * 用户注册接
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            return null;
        }

        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();

        if (StrUtil.hasBlank(userAccount,password)){
            return null;
        }
        return userService.userLogin(userAccount,password,request);
    }

    /**
     * 查询用户接口
     * @param username
     * @param request
     * @return
     */
    @GetMapping("/search")
    public List<User> getUsers(String username,HttpServletRequest request){
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        return userService.findByUsername(username);
    }

    /**
     * 删除用户接口
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public boolean deleteUser(long id, HttpServletRequest request){
        if (id < 0 || !isAdmin(request)){
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 判断用户是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user != null && user.getUserRole() == ADMIN_ROLE) {
            return true;
        }
        return false;
    }

}
