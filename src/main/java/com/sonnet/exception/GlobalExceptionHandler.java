package com.sonnet.exception;

import com.sonnet.common.BaseResponse;
import com.sonnet.common.ErrorCode;
import com.sonnet.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// 利用 Spring 的切面功能
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 此注解的参数用于指定捕获哪一种异常
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e) {
        // 理清楚这个逻辑链
        // 1.当捕获到对应异常时，调用此方法
        // 2.程序遇到异常，进入此方法，最终是调用 ResultUtils 的方法返回结果
        // 3.这个结果仍然会返回给前端
        // 4.其实，遇到错误是，前端本来接受的就是异常返回的结果，只不过这个结果是服务器自己封装的
        // 5.现在，我们自己定义了这个结果
        log.error("BusinessException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }
}
