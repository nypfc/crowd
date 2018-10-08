package com.gedoumi.tg.common.enums;

import lombok.Getter;

/**
 * 宝箱类型枚举
 *
 * @author Minced
 */
@Getter
public enum TreasureTypeEnum {

    TYPE_1(1, 5L, "5分宝箱"),
    TYPE_2(2, 20L, "20分宝箱"),
    TYPE_3(3, 50L, "50分宝箱"),
    TYPE_4(4, 100L, "100分宝箱"),
    TYPE_5(5, 200L, "200分宝箱"),
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
