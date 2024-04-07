package com.sonnet.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求体
 *
 * @author techsonet
 */

@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 1611278796350382796L;

    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String password;
}
