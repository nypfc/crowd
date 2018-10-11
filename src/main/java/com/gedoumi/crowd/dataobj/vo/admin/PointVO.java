package com.gedoumi.crowd.dataobj.vo.admin;

import lombok.Data;

import java.util.Date;

/**
 * 管理后台积分列表
 *
 * @author Minced
 */
@Data
public class PointVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 积分量
     */
    private Long point;

    /**
     * 类型
     * 1:每日助力
     * 2:邀请好友
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

}
