package com.gedoumi.tg.common.enums;

import lombok.Getter;

/**
 * 状态码枚举
 *
 * @author Minced
 */
@Getter
public enum CodeEnum {

    SERVER_ERROR(-1, "服务器错误"),
    METHOD_NOT_SUPPORTED(-2, "请求方式不支持"),
    SUCCESS(0, "请求成功"),
    NO_AUTH_TOKEN(1, "未获取到Token"),
    USER_NOT_LOGIN(2, "用户未登录"),
    DB_ERROR(3, "数据库查询/操作错误"),
    ALREADY_POINTED(4, "今日已经助力"),

    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码对应信息
     */
    private String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
