package cn.gyw.individual.commons.exceptions;

import cn.gyw.individual.commons.enums.BaseEnum;

/**
 * 基础异常
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private BaseEnum<?> codeEnum;

    public BaseException(BaseEnum<?> codeEnum) {
        this(codeEnum, null, null);
    }

    public BaseException(BaseEnum<?> codeEnum, String message) {
        this(codeEnum, message, null);
    }

    public BaseException(BaseEnum<?> codeEnum, String message, Throwable t) {
        super(String.join("|", codeEnum.getName(), message), t);
        this.codeEnum = codeEnum;
    }

    public BaseEnum<?> getCodeEnum() {
        return codeEnum;
    }
}
