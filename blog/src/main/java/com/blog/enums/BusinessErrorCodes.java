package com.blog.enums;

/**
 * 统一并自定义返回状态码，如有需求可以另外增加
 */
public enum BusinessErrorCodes {

    /* 成功状态码 */
    SUCCESS(1, "success"),

    /* 默认失败状态码 */
    ERROR(2, "服务器错误"),

    /* 基础错误 */
    INSERT_FAILED(201, "新增错误"),
    DELETE_FAILED(201, "删除错误"),
    UPDATE_FAILED(201, "更新错误"),
    SELECT_FAILED(201, "查找错误"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999 */
    USER_NOT_LOGIN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_NOT_EMPTY(20006, "用户名不能为空"),
    USER_MUST_BE_IS_NUMBER_AND_LETTER(20007, "密码只能为数字或者字母"),
    USER_BATCH_DELETE_ERROR(20008, "批量删除报错"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "业务逻辑出现问题"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统内部错误，请稍后重试"),

    /* 数据错误：50001-599999 */
    DATA_NONE(50001, "数据未找到"),
    DATA_WRONG(50002, "数据错误"),
    DATA_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限"),

    /* 角色部分错误：80001-89999 */
    ROLE_IS_EXIST(80001, "角色不存在"),
    ROLE_MSG_NOT_AGREEMENT(80002, "角色信息不一致");

    private final Integer code;

    private final String message;

    BusinessErrorCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}