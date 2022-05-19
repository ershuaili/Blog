package com.blog.exception;

import com.blog.enums.BusinessErrorCodes;
import lombok.Data;

@Data
public class ExceptionResult<T> {

    private int code;

    private String message;

    private T data;

    public ExceptionResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
