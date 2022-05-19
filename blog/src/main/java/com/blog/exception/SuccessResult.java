package com.blog.exception;

import lombok.Data;

/**
 * 响应接口返回类
 *
 * @param <T> T
 */
@Data
public class SuccessResult<T> {
    private int code;
    private String message;
    private T data;

    public SuccessResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

