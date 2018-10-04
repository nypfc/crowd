package com.gedoumi.tg.dataobj.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
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
     * 变动类型，1：每日点赞，2：邀请好友
     */
    private Long type;

    /**
     * 产生时间
     */
    private Date createTime;

}
