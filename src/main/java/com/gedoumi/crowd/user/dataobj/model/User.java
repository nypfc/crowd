package com.gedoumi.crowd.user.dataobj.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author Minced
 */
@Data
public class User {

    /**
     * ID
     */
    private Long id;

    /**
     * 第三方UID
     */
    private String uid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 积分
     */
    private Long point;

    /**
     * 注册时间
     */
    private Date registerTime = new Date();

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 邀请码
     */
    private String invitedCode;

    /**
     * 登陆Token
     */
    private String token;

}
