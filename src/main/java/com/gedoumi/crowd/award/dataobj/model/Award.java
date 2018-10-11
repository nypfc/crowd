package com.gedoumi.crowd.award.dataobj.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 奖品
 *
 * @author Minced
 */
@Data
public class Award {

    /**
     * ID
     */
    private Long id;

    /**
     * 奖品类型
     * 1:一号宝箱
     * 2:二号宝箱
     * 3:三号宝箱
     * 4:四号宝箱
     * 5:五号宝箱
     */
    private Integer awardType;

    /**
     * 名称
     */
    private String awardName;

    /**
     * 库存
     */
    private Long awardStock;

    /**
     *
     */
    private BigDecimal awardBegin;

    /**
     *
     */
    private BigDecimal awardEnd;

}
