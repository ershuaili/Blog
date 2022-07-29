package com.blog.exception;

import com.blog.enums.BusinessErrorCodes;
import lombok.Data;

import static com.blog.enums.BusinessErrorCodes.SUCCESS;

@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> ok() {
        return restResult(null, SUCCESS.getCode(), SUCCESS.getMessage());
    }

    public static <T> Result<T> ok(T data) {
        return restResult( data, SUCCESS.getCode(), SUCCESS.getMessage());
    }

    private static <T> Result<T> restResult(T data, Integer code, String message) {
        Result<T> apiResult = new Result<>();
        apiResult.setData(data);
        apiResult.setCode(code);
        apiResult.setMessage(message);
        return apiResult;
    }

}
