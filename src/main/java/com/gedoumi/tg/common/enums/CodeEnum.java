package com.gedoumi.tg.common.enums;

import lombok.Getter;

/**
 * 状态码枚举
 *
 * @author Minced
 */
@Getter
public enum CodeEnum {

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
