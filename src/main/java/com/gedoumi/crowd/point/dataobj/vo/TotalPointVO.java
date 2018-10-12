package com.gedoumi.crowd.point.dataobj.vo;

import lombok.Data;

/**
 * 积分数据
 *
 * @author Minced
 */
@Data
public class TotalPointVO {

    /**
     * 总积分
     */
    private Long totalPoint;

    /**
     * 总用户量
     */
    private Long totalUser;

}
