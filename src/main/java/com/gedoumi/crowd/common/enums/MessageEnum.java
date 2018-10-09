package com.gedoumi.crowd.common.enums;

import lombok.Getter;

/**
 * 消息枚举
 *
 * @author Minced
 */
@Getter
public enum MessageEnum {

    CONNECTION_SUCCESS(1, "WebSocket连接成功"),
    USER_AWARDED(2, "用户中奖"),
    ;

    /**
     * 消息类型
     */
    private Integer type;

    /**
     * 描述
     */
    private String description;

    MessageEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

}
