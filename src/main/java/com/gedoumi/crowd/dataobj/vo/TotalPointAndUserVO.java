package com.gedoumi.crowd.dataobj.vo;

import lombok.Data;

/**
 * 总积分与总助力用户量
 *
 * @author Minced
 */
@Data
public class TotalPointAndUserVO {

    /**
     * 总积分
     */
    private Long totalPoint;

    /**
     * 总用户量
     */
    private Long totalUser;

}
