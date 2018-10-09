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
 * 用户
 *
 * @author Minced
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class User {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 邀请码
     */
    private String invitedCode;

    /**
     * 登陆Token
     */
    private String token;

}
