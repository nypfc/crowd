package com.gedoumi.crowd.api.dataobj.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 总积分
 *
 * @author Minced
 */
@Entity
@DynamicUpdate
@Data
public class TotalPoint {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 总积分
     */
    private Long totalPoint;

}
