package com.gedoumi.crowd.user.dataobj.model;

import lombok.Data;

import java.util.Date;

/**
 * 邀请人与被邀请人
 *
 * @author Minced
 */
@Data
public class UserInvite {

    /**
     * 注册者用户ID
     */
    private Long registerId;

    /**
     * 邀请人用户ID
     */
    private Long inviterId;

    /**
     * 邀请时间
     */
    private Date invitedTime;

}
