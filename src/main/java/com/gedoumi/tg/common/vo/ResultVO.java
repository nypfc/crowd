package com.gedoumi.tg.common.vo;

import lombok.Data;

/**
 * 返回对象
 *
 * @param <T>
 * @author Minced
 */
@Data
public class ResultVO<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

}
