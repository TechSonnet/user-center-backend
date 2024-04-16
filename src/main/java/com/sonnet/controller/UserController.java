package com.sonnet.controller;

import cn.hutool.core.util.StrUtil;

import com.sonnet.common.BaseResponse;
import com.sonnet.common.ErrorCode;
import com.sonnet.common.ResultUtils;
import com.sonnet.exception.BusinessException;
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
    public BaseResponse userRegister(@RequestBody UserRegisterRequest userRegisterRequest){

        if (userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"没有请求参数");
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        if(StrUtil.hasBlank(userAccount,password,checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "部分请求参数为空");
        }
        long registerResult = userService.userRegister(userAccount,password,checkPassword);
        return ResultUtils.success(registerResult);
    }


    /**
     * 用户注册接
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"没有请求参数");
        }

        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();

        if (StrUtil.hasBlank(userAccount,password)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        User loginRestult = userService.userLogin(userAccount,password,request);
        return ResultUtils.success(loginRestult);
    }

    /**
     * 查询用户接口
     * @param username
     * @param request
     * @return
     */
    @GetMapping("/search")
    public BaseResponse getUsers(String username,HttpServletRequest request){
        
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH,"无权限");
        }
        List<User> byUsername = userService.findByUsername(username);
        return ResultUtils.success(byUsername);
    }

    /**
     * 删除用户接口
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public BaseResponse deleteUser(long id, HttpServletRequest request){
        System.out.println("username");
        if (id < 0 || !isAdmin(request)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"用户不存在或者无权限"); 
        }
       boolean removeById = userService.removeById(id);
       return ResultUtils.success(removeById);
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
