package com.gedoumi.crowd.dataobj.vo.api;

import lombok.Data;

import java.util.Date;

/**
 * 用户抽奖详情
 *
 * @author Minced
 */
@Data
public class UserAwardVO {

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
