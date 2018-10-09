package com.gedoumi.tg.dataobj.form;

import lombok.Data;

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
     */
    @NotBlank(message = "UID不能为空")
    private String uid;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    private String avatar;

    /**
     * 邀请码
     */
    private String invitedCode;

}
