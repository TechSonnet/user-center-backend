package com.sonnet.common;

public class ResultUtils {
    /**
     * 返回成功处理结果
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(0, data, "ok");
    }

    /**
     * 返回错误处理结果
     * @param errorCode
     * @return
     */
    public static  BaseResponse error(ErrorCode errorCode) {
        //return new BaseResponse(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
        // 上下两行代码是等效的，因为在BaseResponse类中重写了构造方法
        return new BaseResponse(errorCode);
    }

    /**
     * 返回错误处理结果
     * @param errorCode
     * @param message
     * @param description
     * @return
     */
    public static  BaseResponse error(ErrorCode errorCode,String message ,String description) {
        return new BaseResponse(errorCode.getCode(),null,message ,description);
    }
    
    /**
     * 返回错误处理结果
     * @param code
     * @param message
     * @return
     */
    public static  BaseResponse error(int code,String message) {
        return new BaseResponse(code,null,message);
    }

}
