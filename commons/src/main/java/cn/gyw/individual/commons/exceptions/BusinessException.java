package cn.gyw.individual.commons.exceptions;

import cn.gyw.individual.commons.enums.BaseEnum;

/**
 * 业务相关异常
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BusinessException(BaseEnum<?> codeEnum) {
        super(codeEnum, codeEnum.getName());
    }

    public BusinessException(BaseEnum<?> codeEnum, String message) {
        super(codeEnum, message);
    }

    public BusinessException(BaseEnum<?> codeEnum, String message, Throwable t) {
        super(codeEnum, message, t);
    }
}
