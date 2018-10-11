package com.gedoumi.crowd.common.utils;

import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.user.dataobj.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.NO_LOGIN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 上下文相关工具类
 *
 * @author Minced
 */
@Slf4j
public final class ContextUtil {

    /**
     * 获取HttpServletRequest对象
     *
     * @return HttpServletRequest对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取HttpServletResponse对象
     *
     * @return HttpServletResponse对象
     */
    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 从Request作用域中获取用户
     *
     * @return 用户对象
     */
    public static User getUserFromRequest() {
        HttpServletRequest request = getHttpServletRequest();
        User user = (User) request.getAttribute("user");
        if (user == null) {
            log.error("未能从Request作用域中获取到user");
            throw new TgException(BAD_REQUEST, NO_LOGIN);
        }
        return user;
    }

}
