package com.gedoumi.crowd.common.utils;

import java.util.UUID;

import static com.gedoumi.crowd.common.constants.ProjectConstants.AUTH_TOKEN;

/**
 * 用户登录工具类
 *
 * @author Minced
 */
public final class LoginUtil {

    /**
     * 产生令牌
     *
     * @return 令牌
     */
    public static String createToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 往Cookie中设置令牌，令牌有效期1小时
     *
     * @param authToken 令牌
     */
    public static void setAuthTokenCookie(String authToken) {
        CookieUtil.setCookie(AUTH_TOKEN, authToken, 3600);
    }

}
