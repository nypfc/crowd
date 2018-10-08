package com.gedoumi.tg.component;

import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dao.UserDao;
import com.gedoumi.tg.dataobj.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gedoumi.tg.common.constants.ResponseMessage.NO_LOGIN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 请求拦截器
 *
 * @author Minced
 */
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Resource
    private UserDao userDao;

    /**
     * 用户登陆拦截器
     * 获取Header中的Token
     * 通过Token查询用户
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("auth-token");
        if (StringUtils.isEmpty(token)) {
            log.warn("未获取到Token");
            throw new TgException(BAD_REQUEST, NO_LOGIN);
        }
        if (userDao.countByToken(token) != 1) {
            log.warn("未能通过Token查询到用户");
            throw new TgException(BAD_REQUEST, NO_LOGIN);
        }
        // 查询用户并将用户存入request作用域中
        User user = userDao.findByToken(token);
        request.setAttribute("user", user);
        return true;
    }

}
