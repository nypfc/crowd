package com.gedoumi.tg.component;

import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dao.UserDao;
import com.gedoumi.tg.dataobj.model.User;
import com.gedoumi.tg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.gedoumi.tg.common.constants.ResponseMessage.NO_LOGIN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * WebSocket拦截器
 *
 * @author Minced
 */
@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // request参数强转并获取Token
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String token = servletRequest.getParameter("auth-token");
        // 查询用户
        User user = userService.getUser(token);
        // 传递用户ID
        attributes.put("userId", user.getId());
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.debug("after handshake");
    }

}
