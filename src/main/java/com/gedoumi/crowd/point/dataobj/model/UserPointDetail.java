package com.gedoumi.crowd.point.dataobj.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户积分明细
 *
 * @author Minced
 */
@Data
public class UserPointDetail {

    /**
     * 积分详情ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 变动的积分数量
     */
    private Long point;

    /**
     * 变动类型，1：每日助力，2：邀请好友
     */
    private Integer type;

    /**
     * 产生时间
     */
    private Date createTime;

}
