package com.gedoumi.extension.component;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;

/**
 * WebSocket处理类
 *
 * @author Minced
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    // TODO 后续改为Redis存储
    private static final Map<String, WebSocketSession> websocketSessionMap = Maps.newHashMap();

    /**
     * 连接成功时发送的处理
     *
     * @param session Websocket Session
     * @throws Exception ex
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    /**
     * 处理收到的消息
     *
     * @param session Websocket Session
     * @param message 接受的消息
     * @throws Exception ex
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    /**
     * 连接错误时的处理
     *
     * @param session   Websocket Session
     * @param exception 出错的异常
     * @throws Exception ex
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    /**
     * 连接关闭时的处理
     *
     * @param session WebSocket Session
     * @param status  关闭的状态
     * @throws Exception ex
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

}
