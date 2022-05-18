package com.blog.exception;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.spring.web.plugins.Docket;


@ControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, @NotNull Class converterType) {
        return !returnType.getGenericParameterType().equals(Result.class) && !returnType.getDeclaringClass().equals(Docket.class);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType mediaType, @NotNull Class selectedConverterType, ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        if (request.getURI().getRawPath().contains("swagger") || request.getURI().getRawPath().contains("api-docs") || request.getURI().getRawPath().contains("/diagram")) {
            return body;
        } else {
            return Result.success(body);
        }
    }

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Result<Object> bizExceptionHandler(BusinessException e) {
        return Result.error(e.getErrorCode(), e.getMessage());
    }
}