package com.sonnet.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResult {
    /**
     * 注册结果 'ok' | 'error'
     */
    private String status;
    /**
     * 用户权限 user' | 'guest' | 'admin'
     */
    private String currentAuthority;
}
