package com.gedoumi.crowd.common.config;

import com.gedoumi.crowd.component.WebSocketHandler;
import com.gedoumi.crowd.component.WebSocketInterceptor;
import org.springframework.context.annotation.Bean;
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
                .addInterceptors(webSocketInterceptor())
                .setAllowedOrigins("*");
        // WebSocket模拟
        webSocketHandlerRegistry
                .addHandler(new WebSocketHandler(), "/ws/sockjs")
                .addInterceptors(webSocketInterceptor())
                .setAllowedOrigins("*").withSockJS();
    }

    /**
     * 注册WebSocket拦截器
     *
     * @return WebSocket拦截器
     */
    @Bean
    public WebSocketInterceptor webSocketInterceptor() {
        return new WebSocketInterceptor();
    }

}
