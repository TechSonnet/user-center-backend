package com.sonnet.model.request;


import com.sun.source.doctree.SerialDataTree;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author chang
 */
@Data
public class UserRegisterRequest implements Serializable {


    private static final long serialVersionUID = 5860696408942223781L;

    /**
     * 用户账户
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户校验密码
     */
    private String checkPassword;
}
