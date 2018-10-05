package com.gedoumi.tg.component;

import com.gedoumi.tg.common.enums.CodeEnum;
import com.gedoumi.tg.common.exception.DataBaseException;
import com.gedoumi.tg.common.exception.TgException;
import com.gedoumi.tg.dataobj.vo.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 异常处理类
 *
 * @author Minced
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 数据库查询/操作异常
     *
     * @param ex 异常
     * @return 响应体
     */
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ResponseObject> dataBaseException(DataBaseException ex) {
        String message = ex.getMessage();
        log.error("错误信息:{}", ex.getMessage());
        ResponseObject responseObject = ResponseObject.setErrorResponse(ex.getCodeEnum(), message);
        return new ResponseEntity<>(responseObject, ex.getHttpStatus());
    }

    /**
     * 项目内异常
     *
     * @param ex 异常
     * @return 响应对象
     */
    @ExceptionHandler(TgException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseObject tgException(TgException ex) {
        return ResponseObject.setErrorResponse(ex.getCodeEnum());
    }

    /**
     * 请求方式不支持异常
     *
     * @param ex 异常
     * @return 响应对象
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseObject httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        StringBuilder message = new StringBuilder("不支持的请求方式，当前请求方式为[");
        message.append(ex.getMethod()).append("]，支持的请求方式为").append(Arrays.toString(ex.getSupportedMethods()));
        return ResponseObject.setErrorResponse(CodeEnum.METHOD_NOT_SUPPORTED, message.toString());
    }

    /**
     * 运行时异常
     *
     * @param ex 异常
     * @return 响应对象
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseObject runtimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseObject.setErrorResponse(CodeEnum.SERVER_ERROR);
    }

    /**
     * 异常
     *
     * @param ex 异常
     * @return 响应对象
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseObject exception(Exception ex) {
        ex.printStackTrace();
        return ResponseObject.setErrorResponse(CodeEnum.SERVER_ERROR);
    }

}
