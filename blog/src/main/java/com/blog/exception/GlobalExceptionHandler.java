package com.blog.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception e) {
        //异常具体位置
        e.getStackTrace();
        ExceptionResult exceptionResult;
        if (e instanceof BusinessException) {
            exceptionResult = ExceptionResult.failure(((BusinessException) e).getResultCode());
        } else {
            exceptionResult = ExceptionResult.failure(500, "服务器意外错误：" + e.getMessage());
        }
        return exceptionResult;
    }

}
