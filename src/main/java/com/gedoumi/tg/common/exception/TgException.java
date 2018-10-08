package com.gedoumi.tg.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 普通异常
 *
 * @author Minced
 */
@Getter
@Setter
public class TgException extends RuntimeException {

    private HttpStatus httpStatus;

    private String message;

    public TgException() {
        super();
    }

    public TgException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
