package com.gedoumi.tg.dataobj.vo;

import lombok.Data;

/**
 * 响应对象
 *
 * @param <T>
 * @author Minced
 */
@Data
public class ResponseObject<T> {

    /**
     * 状态码
     */
    private Boolean success;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 封装成功响应数据（不带数据）
     *
     * @param <T> 数据类型
     * @return 响应数据对象
     */
    public static <T> ResponseObject setSuccessResponse() {
        return setSuccessResponse(null);
    }

    /**
     * 封装成功响应数据（带数据）
     *
     * @param data 响应的具体数据
     * @param <T>  数据类型
     * @return 响应数据对象
     */
    public static <T> ResponseObject setSuccessResponse(T data) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(true);
        responseObject.setData(data);
        return responseObject;
    }

    /**
     * 封装失败响应数据（使用默认信息）
     *
     * @param <T> 数据类型
     * @return 响应数据对象
     */
    public static <T> ResponseObject setErrorResponse() {
        return setErrorResponse(null);
    }

    /**
     * 封装失败响应数据（使用自定义信息）
     *
     * @param message 错误信息
     * @param <T>     数据类型
     * @return 响应数据对象
     */
    public static <T> ResponseObject setErrorResponse(String message) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        responseObject.setMessage(message);
        return responseObject;
    }

}
