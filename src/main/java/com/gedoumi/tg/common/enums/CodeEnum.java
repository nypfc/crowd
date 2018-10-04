package com.gedoumi.tg.common.enums;

import lombok.Getter;

/**
 * 状态码枚举
 *
 * @author Minced
 */
@Getter
public enum CodeEnum {

    SUCCESS(0, "请求成功"),
    NO_AUTH_TOKEN(1, "为获取到Token"),
    USER_NOT_EXIST(2, "用户不存在"),
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码描述
     */
    private String description;

    CodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

}
