package com.gedoumi.tg.common.exception;

import com.gedoumi.tg.common.enums.CodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 普通异常
 *
 * @author Minced
 */
@Getter
@Setter
public class TgException extends RuntimeException {

    private CodeEnum codeEnum;

    public TgException() {
        super();
    }

    public TgException(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

}
