package com.gedoumi.tg.dataobj.form;

import lombok.Data;

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
     * 邀请码
     */
    private String invitedCode;

}
