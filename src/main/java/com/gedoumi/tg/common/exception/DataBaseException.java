package com.gedoumi.tg.common.exception;

import com.gedoumi.tg.common.enums.CodeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 数据库异常
 *
 * @author Minced
 */
@Getter
@Setter
public class DataBaseException extends RuntimeException {

    /**
     * 状态码枚举
     */
    private CodeEnum codeEnum;

    /**
     * Http状态码
     */
    private HttpStatus httpStatus;

    /**
     * 异常信息
     */
    private String message;

    public DataBaseException() {
        super();
    }

    public DataBaseException(CodeEnum codeEnum, HttpStatus httpStatus, String message) {
        this.codeEnum = codeEnum;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
