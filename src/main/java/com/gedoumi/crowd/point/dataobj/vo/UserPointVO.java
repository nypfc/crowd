package com.gedoumi.crowd.point.dataobj.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用户积分明细
 *
 * @author Minced
 */
@Data
public class UserPointVO {

    /**
     * 产生时间
     */
    private Date createTime;

    /**
     * 积分量
     */
    private Long point;

    /**
     * 类型
     */
    private Integer type;

}
