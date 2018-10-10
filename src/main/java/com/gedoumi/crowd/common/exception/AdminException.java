package com.gedoumi.crowd.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 后台管理页面异常
 *
 * @author Minced
 */
@Getter
@Setter
public class AdminException extends RuntimeException {

    private HttpStatus httpStatus;

    private String message;

    public AdminException() {
        super();
    }

    public AdminException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
