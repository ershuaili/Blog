package com.blog.exception;

import com.blog.enums.BusinessErrorCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义service层异常类 RuntimeException 可以不捕获也没事
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private int errorCode;
    private String message;

    public BusinessException(BusinessErrorCodes businessErrorCodes) {
        this.errorCode = businessErrorCodes.getCode();
        this.message = businessErrorCodes.getMessage();
    }
}

