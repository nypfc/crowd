package com.gedoumi.crowd.common.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

/**
 * Hash工具类
 *
 * @author Minced
 */
@SuppressWarnings("UnstableApiUsage")
public final class HashUtil {

    private HashUtil() {
    }

    /**
     * 密码加密
     *
     * @param username 用户名
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encryPassword(String username, String password) {
        return Hashing.sha256().hashString(username + password, Charset.forName("UTF-8")).toString();
    }

}
