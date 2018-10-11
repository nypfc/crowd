package com.gedoumi.crowd.award.dataobj.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户抽奖明细
 *
 * @author Minced
 */
@Data
public class UserAwardDetail {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 奖品类型
     */
    private Integer awardType;

    /**
     * 产生时间
     */
    private Date createTime;

    /**
     * 是否中奖
     * 0:未中奖
     * 1:中奖
     */
    private Integer isSuccess;

}
