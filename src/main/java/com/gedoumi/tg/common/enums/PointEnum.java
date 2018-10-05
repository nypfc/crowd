package com.gedoumi.tg.common.enums;

import lombok.Getter;

/**
 * 积分枚举
 *
 * @author Minced
 */
@Getter
public enum PointEnum {

    DAY(1,"每日助力", 4L),
    SHARE(2, "分享邀请好友", 2L),
    ;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 描述
     */
    private String description;

    /**
     * 积分量
     */
    private Long point;

    PointEnum(Integer type, String description, Long point) {
        this.type = type;
        this.description = description;
        this.point = point;
    }

}
