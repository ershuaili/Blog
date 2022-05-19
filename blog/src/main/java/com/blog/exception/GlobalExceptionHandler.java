package com.blog.exception;

import com.blog.enums.BusinessErrorCodes;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.spring.web.plugins.Docket;


@RestControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, @NotNull Class converterType) {
        return !returnType.getGenericParameterType().equals(SuccessResult.class) && !returnType.getDeclaringClass().equals(Docket.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType mediaType, @NotNull Class selectedConverterType, ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        if (request.getURI().getRawPath().contains("swagger") || request.getURI().getRawPath().contains("api-docs")) {
            return body;
        } else if (body instanceof ExceptionResult) {
            return body;
        } else {
            return new SuccessResult<>(BusinessErrorCodes.SUCCESS.getCode(), BusinessErrorCodes.SUCCESS.getMessage(), body);
        }
    }

    /**
     * 全局异常
     *
     * @return 系统错误
     */
    @ExceptionHandler(value = Exception.class)
    public ExceptionResult<Object> exceptionHandler() {
        return new ExceptionResult<>(BusinessErrorCodes.ERROR.getCode(), BusinessErrorCodes.ERROR.getMessage());
    }

    /**
     * 用户自定义错误
     *
     * @param e 错误代码
     * @return 错误信息
     */
    @ExceptionHandler(value = BusinessException.class)
    public ExceptionResult<Object> bizExceptionHandler(BusinessException e) {
        return new ExceptionResult<>(e.getErrorCode(), e.getMessage());
    }
}