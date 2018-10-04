package com.gedoumi.tg.component;

import com.gedoumi.tg.common.exception.LoginException;
import com.gedoumi.tg.dataobj.vo.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理类
 *
 * @author Minced
 */
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 未登录异常
     *
     * @param ex 异常
     * @return 响应对象
     */
    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject notLogin(LoginException ex) {
        return ResponseObject.setErrorResponse(ex.getCodeEnum());
    }

}
