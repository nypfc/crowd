package com.gedoumi.tg.dataobj.vo;

import com.gedoumi.tg.common.enums.CodeEnum;
import lombok.Data;
import org.springframework.util.StringUtils;

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
    private Integer code;

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
        responseObject.setCode(CodeEnum.SUCCESS.getCode());
        responseObject.setMessage(CodeEnum.SUCCESS.getMessage());
        responseObject.setData(data);
        return responseObject;
    }

    /**
     * 封装失败响应数据（使用默认信息）
     *
     * @param codeEnum 状态码枚举
     * @param <T>      数据类型
     * @return 响应数据对象
     */
    public static <T> ResponseObject setErrorResponse(CodeEnum codeEnum) {
        return setErrorResponse(codeEnum, null);
    }

    /**
     * 封装失败响应数据（使用自定义信息）
     *
     * @param codeEnum 状态码枚举
     * @param message  错误信息
     * @param <T>      数据类型
     * @return 响应数据对象
     */
    public static <T> ResponseObject setErrorResponse(CodeEnum codeEnum, String message) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setCode(codeEnum.getCode());
        if (StringUtils.isEmpty(message)) {
            responseObject.setMessage(codeEnum.getMessage());
        } else {
            responseObject.setMessage(message);
        }
        return responseObject;
    }

}
