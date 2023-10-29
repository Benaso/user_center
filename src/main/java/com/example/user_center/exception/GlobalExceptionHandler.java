package com.example.user_center.exception;

import com.example.user_center.common.BaseResponse;
import com.example.user_center.common.ErrorCode;
import com.example.user_center.common.ResultUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * author: Victor;
 * version: 1.0
 * 全局异常处理器
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e ){
        log.error("businessException" + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(),e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e){
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, e.getMessage(),"");
    }
}
