package com.gedoumi.tg.dataobj.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 奖品
 *
 * @author Minced
 */
@Data
@DynamicInsert
@DynamicUpdate
@Entity
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 奖品类型
     * 1:一号宝箱
     * 2:二号宝箱
     * 3:三号宝箱
     * 4:四号宝箱
     * 5:五号宝箱
     */
    private Long awardType;

    /**
     * 名称
     */
    private String awardName;

    /**
     * 库存
     */
    private Long awardStock;

}
