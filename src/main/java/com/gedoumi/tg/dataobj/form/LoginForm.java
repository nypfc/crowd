package com.gedoumi.tg.dataobj.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 登陆表单
 *
 * @author Minced
 */
@Data
public class LoginForm {

    /**
     * 第三方UID
     * 去空格的非空验证
     * 长度不能超过64位
     */
    @NotBlank
    @Length(max = 64)
    private String uid;

    /**
     * 昵称
     * 去空格的非空验证
     * 长度不能超过30位
     */
    @NotBlank
    @Length(max = 30)
    private String nickname;

    /**
     * 头像
     * 去空格的非空验证
     * 长度不能超过300位
     */
    @NotBlank
    @Length(max = 300)
    private String avatar;

    /**
     * 邀请码
     * 可以为null
     * 若不为null，则长度必须为10位
     */
    @Length(min = 10, max = 10)
    private String invitedCode;

}
