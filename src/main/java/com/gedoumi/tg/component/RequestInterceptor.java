package com.gedoumi.tg.component;

import com.gedoumi.tg.common.enums.CodeEnum;
import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dao.UserDao;
import com.gedoumi.tg.dataobj.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if (StringUtils.isEmpty(token)) throw new TgException(CodeEnum.NO_AUTH_TOKEN);
        if (userDao.countByToken(token) != 1) throw new TgException(CodeEnum.USER_NOT_LOGIN);
        // 查询用户并将用户存入request作用域中
        User user = userDao.findByToken(token);
        request.setAttribute("user", user);
        return true;
    }

}
