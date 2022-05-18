package com.blog.exception;

import com.blog.enums.BusinessErrorCodes;
import lombok.Data;

/**
 * 响应接口返回类
 *
 * @param <T> T
 */
@Data
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(BusinessErrorCodes.SUCCESS.getCode());
        result.setMessage(BusinessErrorCodes.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message);
    }
}

