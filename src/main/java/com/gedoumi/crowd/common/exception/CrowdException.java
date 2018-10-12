package com.gedoumi.crowd.common.exception;

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
public class CrowdException extends RuntimeException {

    private HttpStatus httpStatus;

    private String message;

    public CrowdException() {
        super();
    }

    public CrowdException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
