package com.gedoumi.tg.common.config;

import com.gedoumi.tg.component.WebSocketHandler;
import com.gedoumi.tg.component.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket配置
 *
 * @author Minced
 */
@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * 注册WebSocket处理类
     *
     * @param webSocketHandlerRegistry WebSocket处理器注册类
     */
    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry webSocketHandlerRegistry) {
        // WebSocket
        webSocketHandlerRegistry
                .addHandler(new WebSocketHandler(), "/ws")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
        // WebSocket模拟
        webSocketHandlerRegistry
                .addHandler(new WebSocketHandler(), "/ws/sockjs")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*").withSockJS();
    }

}
