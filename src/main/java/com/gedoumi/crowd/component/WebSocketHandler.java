package com.gedoumi.crowd.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gedoumi.crowd.common.exception.TgException;
import com.gedoumi.crowd.api.dataobj.vo.AwardUserVO;
import com.gedoumi.crowd.api.dataobj.vo.MessageVO;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;

import static com.gedoumi.crowd.common.constants.ResponseMessage.PUSH_MESSAGE_ERROR;
import static com.gedoumi.crowd.common.enums.MessageEnum.CONNECTION_SUCCESS;
import static com.gedoumi.crowd.common.enums.MessageEnum.USER_AWARDED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * WebSocket处理类
 *
 * @author Minced
 */
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    // TODO 后续改为Redis存储
    private static final Map<Long, WebSocketSession> websocketSessionMap = Maps.newHashMap();

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 连接成功时发送的处理
     *
     * @param session Websocket Session
     * @throws Exception ex
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 获取用户ID
        Long userId = (Long) session.getAttributes().get("userId");
        websocketSessionMap.put(userId, session);
        log.debug("用户ID: {} 已连接", userId);
        log.debug("目前有{}个用户正在连接", websocketSessionMap.size());
        // 握手成功后推送信息
        MessageVO messageVO = MessageVO.setMessageVO(CONNECTION_SUCCESS);
        session.sendMessage(new TextMessage(mapper.writeValueAsString(messageVO)));
    }

    /**
     * 处理收到的消息
     *
     * @param session Websocket Session
     * @param message 接受的消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    }

    /**
     * 连接错误时的处理
     *
     * @param session   Websocket Session
     * @param exception 出错的异常
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
    }

    /**
     * 连接关闭时的处理
     *
     * @param session WebSocket Session
     * @param status  关闭的状态
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Long userId = (Long) session.getAttributes().get("userId");
        log.info("用户ID: {} 已退出", userId);
        websocketSessionMap.remove(userId);
    }

    /**
     * 用户中奖推送
     *
     * @param nickname  昵称
     * @param awardType 奖品类型
     */
    public void awardedMessage(String nickname, Integer awardType) {
        AwardUserVO awardUserVO = new AwardUserVO();
        awardUserVO.setNickname(nickname);
        awardUserVO.setAwardType(awardType);
        sendMessageToUsers(MessageVO.setMessageVO(USER_AWARDED, awardUserVO));
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param data 消息数据
     */
    private void sendMessageToUsers(Object data) {
        try {
            TextMessage textMessage = new TextMessage(mapper.writeValueAsString(data));
            for (Map.Entry<Long, WebSocketSession> entry : websocketSessionMap.entrySet()) {
                WebSocketSession user = entry.getValue();
                if (user.isOpen()) {
                    user.sendMessage(textMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new TgException(INTERNAL_SERVER_ERROR, PUSH_MESSAGE_ERROR);
        }
    }

}
