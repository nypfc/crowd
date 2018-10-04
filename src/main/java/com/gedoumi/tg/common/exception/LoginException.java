package com.gedoumi.tg.common.exception;

import com.gedoumi.tg.common.enums.CodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 未登录异常
 *
 * @author Minced
 */
@Getter
@Setter
public class LoginException extends RuntimeException {

    private CodeEnum codeEnum;

    public LoginException() {
        super();
    }

    public LoginException(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

}
