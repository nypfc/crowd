package com.gedoumi.crowd.dataobj.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gedoumi.crowd.common.enums.MessageEnum;
import lombok.Data;

/**
 * 消息
 *
 * @param <T> 数据类型
 * @author Minced
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MessageVO<T> {

    /**
     * 消息类型
     */
    private Integer type;

    /**
     * 信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 创建消息（不带数据）
     *
     * @param messageEnum 消息枚举
     * @param <T>         数据类型
     * @return 消息对象
     */
    public static <T> MessageVO<T> setMessageVO(MessageEnum messageEnum) {
        return setMessageVO(messageEnum, null);
    }

    /**
     * 创建消息（带数据）
     *
     * @param messageEnum 消息枚举
     * @param data        数据
     * @param <T>         数据类型
     * @return 消息对象
     */
    public static <T> MessageVO<T> setMessageVO(MessageEnum messageEnum, T data) {
        MessageVO<T> messageVO = new MessageVO<>();
        messageVO.setType(messageEnum.getType());
        messageVO.setMessage(messageEnum.getDescription());
        messageVO.setData(data);
        return messageVO;
    }

}
