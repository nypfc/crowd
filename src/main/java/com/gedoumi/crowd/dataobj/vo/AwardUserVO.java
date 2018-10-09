package com.gedoumi.crowd.dataobj.vo;

import lombok.Data;

/**
 * 中奖用户VO
 *
 * @author Minced
 */
@Data
public class AwardUserVO {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 奖品类型
     */
    private Integer awardType;

}
