package com.gedoumi.crowd.user.dataobj.model;

import lombok.Data;

import java.util.Date;

/**
 * 管理后台用户
 *
 * @author Minced
 */
@Data
public class AdminUser {

    private Long id;

    private String username;

    private String password;

    private Date registerTime;

    private Date lastLoginTime;

    private String token;

}
