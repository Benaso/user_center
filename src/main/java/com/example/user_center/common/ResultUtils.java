package com.example.user_center.common;

/**
 * author: Victor;
 * version: 1.0
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 返回成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 返回失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error (ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    /**
     * 返回失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error (ErrorCode errorCode, String msg , String description){
        return new BaseResponse<>(errorCode.getCode(), msg, description);
    }

    public static BaseResponse error (int code, String msg , String description){
        return new BaseResponse<>(code, msg, description);
    }


    public static BaseResponse error (ErrorCode errorCode, String description){
        return new BaseResponse<>(errorCode.getCode(), errorCode.getMsg(), description);
    }


}
