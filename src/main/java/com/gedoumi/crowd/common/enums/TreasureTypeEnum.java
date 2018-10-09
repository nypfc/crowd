package com.gedoumi.crowd.common.enums;

import lombok.Getter;

/**
 * 宝箱类型枚举
 *
 * @author Minced
 */
@Getter
public enum TreasureTypeEnum {

    AWARD_TYPE_1(1, 20L, "一号宝箱"),
    AWARD_TYPE_2(2, 50L, "二号宝箱"),
    AWARD_TYPE_3(3, 100L, "三号宝箱"),
    AWARD_TYPE_4(4, 160L, "四号宝箱"),
    AWARD_TYPE_5(5, 250L, "五号宝箱"),
    ;

    private Integer type;

    private Long point;

    private String description;

    TreasureTypeEnum(Integer type, Long point, String description) {
        this.type = type;
        this.point = point;
        this.description = description;
    }

}
