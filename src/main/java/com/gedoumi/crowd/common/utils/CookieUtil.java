package com.gedoumi.crowd.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Cookie 工具类
 *
 * @author Minced
 */
@SuppressWarnings("ConstantConditions")
public final class CookieUtil {

    private CookieUtil() {
    }

    /**
     * 得到Cookie的值, 不编码
     *
     * @param cookieName Cookie名称
     * @return Cookie值
     */
    public static String getCookieValue(String cookieName) {
        return getCookieValue(cookieName, false);
    }

    /**
     * 得到Cookie的值
     *
     * @param cookieName Cookie名称
     * @param isDecoder  是否使用UTF-8编码
     * @return Cookie值
     */
    public static String getCookieValue(String cookieName, boolean isDecoder) {

        HttpServletRequest request = ContextUtil.getHttpServletRequest();

        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie aCookieList : cookieList) {
                if (aCookieList.getName().equals(cookieName)) {
                    if (isDecoder) {
                        retValue = URLDecoder.decode(aCookieList.getValue(), "UTF-8");
                    } else {
                        retValue = aCookieList.getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 得到Cookie的值
     *
     * @param cookieName   Cookie名称
     * @param encodeString 自定义编码方式
     * @return Cookie值
     */
    public static String getCookieValue(String cookieName, String encodeString) {

        HttpServletRequest request = ContextUtil.getHttpServletRequest();

        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for (Cookie aCookieList : cookieList) {
                if (aCookieList.getName().equals(cookieName)) {
                    retValue = URLDecoder.decode(aCookieList.getValue(), encodeString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 设置Cookie的值，不设置生效时间默认浏览器关闭即失效，不编码
     *
     * @param cookieName  Cookie名称
     * @param cookieValue Cookie值
     */
    public static void setCookie(String cookieName, String cookieValue) {
        setCookie(cookieName, cookieValue, -1);
    }

    /**
     * 设置Cookie的值，在指定时间内生效，不编码
     *
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage Cookie生效的最大秒数
     */
    public static void setCookie(String cookieName, String cookieValue, int cookieMaxage) {
        setCookie(cookieName, cookieValue, cookieMaxage, false);
    }

    /**
     * 设置Cookie的值，不设置生效时间，使用UTF-8编码
     *
     * @param cookieName  Cookie名称
     * @param cookieValue Cookie值
     * @param isEncode    是否使用UTF-8编码
     */
    public static void setCookie(String cookieName, String cookieValue, boolean isEncode) {
        setCookie(cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值，在指定时间内生效，使用UTF-8编码
     *
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage Cookie生效的最大秒数
     * @param isEncode     是否使用UTF-8编码
     */
    public static void setCookie(String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {
        doSetCookie(cookieName, cookieValue, cookieMaxage, isEncode);
    }

    /**
     * 设置Cookie的值，在指定时间内生效，自定义编码方式
     *
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage Cookie生效的最大秒数
     * @param encodeString 自定义编码方式
     */
    public static void setCookie(String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        doSetCookie(cookieName, cookieValue, cookieMaxage, encodeString);
    }

    /**
     * 删除Cookie
     *
     * @param cookieName Cookie名称
     */
    public static void deleteCookie(String cookieName) {
        doSetCookie(cookieName, "", 1, false);
    }

    /**
     * 设置Cookie的值（UTF-8），并使其在指定时间内生效
     *
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage Cookie生效的最大秒数
     * @param isEncode     是否使用UTF-8编码
     */
    private static void doSetCookie(String cookieName, String cookieValue, int cookieMaxage, boolean isEncode) {

        HttpServletRequest request = ContextUtil.getHttpServletRequest();
        HttpServletResponse response = ContextUtil.getHttpServletResponse();

        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                // System.out.println(domainName);
                if (!"localhost".equals(domainName) && !"http".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置Cookie的值（自定义编码方式），并使其在指定时间内生效
     *
     * @param cookieName   Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxage Cookie生效的最大秒数
     * @param encodeString 自定义编码方式
     */
    private static void doSetCookie(String cookieName, String cookieValue, int cookieMaxage, String encodeString) {

        HttpServletRequest request = ContextUtil.getHttpServletRequest();
        HttpServletResponse response = ContextUtil.getHttpServletResponse();

        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0)
                cookie.setMaxAge(cookieMaxage);
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName) && !"http".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到cookie的域名
     *
     * @param request 请求对象
     */
    private static String getDomainName(HttpServletRequest request) {
        String domainName;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }

}
