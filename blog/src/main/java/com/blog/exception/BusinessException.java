package com.blog.exception;

import com.blog.enums.BusinessErrorCodes;

/**
 * 自定义service层异常类 RuntimeException 可以不捕获也没事
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private BusinessErrorCodes businessErrorCodes;

    public BusinessException(BusinessErrorCodes businessErrorCodes) {
        this(businessErrorCodes.message());
        this.businessErrorCodes = businessErrorCodes;
    }

    private BusinessException(String message) {
        super(message);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BusinessErrorCodes getResultCode() {
        return businessErrorCodes;
    }
}

