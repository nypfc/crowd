package com.gedoumi.crowd.dataobj.dto;

import com.gedoumi.crowd.dataobj.model.User;
import com.gedoumi.crowd.dataobj.model.UserPointDetail;
import lombok.Data;

/**
 * 后台管理积分明细DTO
 *
 * @author Minced
 */
@Data
public class PointDTO {

    /**
     * 积分详情对象
     */
    private UserPointDetail userPointDetail;

    /**
     * 用户对象
     */
    private User user;

}
