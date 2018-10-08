package com.gedoumi.tg.dataobj.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户抽奖详情（明细）
 *
 * @author Minced
 */
@Data
@DynamicInsert
@DynamicUpdate
@Entity
public class UserAwardDetail {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date createTime = new Date();

    /**
     * 是否中奖
     * 0:未中奖
     * 1:中奖
     */
    private Integer isSuccess;

}
