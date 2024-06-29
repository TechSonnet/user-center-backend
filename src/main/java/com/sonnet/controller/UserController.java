package com.sonnet.controller;

import ch.qos.logback.core.model.INamedModel;
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

    @GetMapping("/current")
    public BaseResponse getCurrentUser(HttpServletRequest request){
        // 1. 从 session 获取当前用户信息
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        // 2. 根据信息，进行查库操作，返回用户信息
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        // 此处之所以进行查库操作，是为了保证信息是最新的。
        // 从缓存（如 Session）中查询，并不能保证这一点
        currentUser = userService.getById(currentUser.getId());
        User user = userService.hindUserInfo(currentUser);
        // 3. 返回用户信息
        return ResultUtils.success(user);
    }



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
     * 用户注销
     * @return 返回值大于 0 则代表注销成功
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"request 为空");
        }
        userService.userLogout(request);
        return ResultUtils.success(1);
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

    @GetMapping("/searchUsers")
    public BaseResponse getUserList(HttpServletRequest request){

        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH,"无权限");
        }
        List<User> userList = userService.list();
        return ResultUtils.success(userList);
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
