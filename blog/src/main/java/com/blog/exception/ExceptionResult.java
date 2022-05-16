package com.blog.exception;

import com.blog.enums.BusinessErrorCodes;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一Controller中RESTFul风格接口返回的结果
 */
@Data
public class ExceptionResult implements Serializable {

    private static final long serialVersionUID = 1L;

    // 状态码
    private Integer code;
    // 错误信息
    private String msg;
    // 响应回去的数据
    private Object data;

    private ExceptionResult() {
    }

    private ExceptionResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 操作失败，自定义code和msg
     */
    public static ExceptionResult failure(Integer code, String msg) {
        return new ExceptionResult(code, msg);
    }

    /**
     * 操作失败，没有返回的数据
     */
    public static ExceptionResult failure(BusinessErrorCodes businessErrorCodes) {
        ExceptionResult exceptionResult = new ExceptionResult();
        exceptionResult.setResultCode(businessErrorCodes);
        return exceptionResult;
    }

    private void setResultCode(BusinessErrorCodes code) {
        this.code = code.code();
        this.msg = code.message();
    }

}

