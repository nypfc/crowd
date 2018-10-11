package com.gedoumi.crowd.component;

import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gedoumi.crowd.common.constants.ProjectConstants.AUTH_TOKEN;
import static com.gedoumi.crowd.common.constants.ResponseMessageConstants.NO_LOGIN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Api请求拦截器
 *
 * @author Minced
 */
@Slf4j
public class ApiRequestInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    /**
     * 用户登陆拦截器
     * 获取Header中的Token
     * 通过Token查询用户
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(AUTH_TOKEN);
        if (StringUtils.isEmpty(token)) {
            log.warn("未获取到Token");
            throw new TgException(BAD_REQUEST, NO_LOGIN);
        }
        // 查询用户并将用户存入request作用域中
        request.setAttribute("user", userService.getUser(token));
        return true;
    }

}
