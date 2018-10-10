package com.gedoumi.crowd.component;

import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.common.utils.CookieUtil;
import com.gedoumi.crowd.dataobj.model.AdminUser;
import com.gedoumi.crowd.service.AdminUserService;
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
 * Admin请求拦截器
 *
 * @author Minced
 */
@Slf4j
public class AdminRequestInterceptor implements HandlerInterceptor {

    @Resource
    private AdminUserService adminUserService;

    /**
     * 后台用户登陆拦截器
     * 获取Cookie中的令牌
     * 通过令牌查询用户
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtil.getCookieValue(AUTH_TOKEN);
        if (StringUtils.isEmpty(token)) {
            log.warn("未获取到Token");
            throw new TgException(BAD_REQUEST, NO_LOGIN);
        }
        // 查询用户并将用户存入request作用域中
        AdminUser user = adminUserService.getUser(token);
        if (user == null) {
            // 查询结果为空
            log.error("\"Token={}\"未查询到用户", token);
            response.sendRedirect("/admin/loginPage");
            return false;
        }
        request.setAttribute("user", user);
        return true;
    }

}
