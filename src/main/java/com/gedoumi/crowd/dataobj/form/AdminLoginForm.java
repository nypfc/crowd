package com.gedoumi.crowd.dataobj.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 管理后台登录表单
 */
@Data
public class AdminLoginForm {

    /**
     * 用户名
     * 去空格的非空验证
     * 长度不能大于20
     */
    @NotBlank
    @Length(max = 20)
    private String username;

    /**
     * 登录密码
     * 去空格的非空验证
     * 长度必须为64位
     */
    @NotBlank
    @Length(min = 64, max = 64)
    private String password;

}
