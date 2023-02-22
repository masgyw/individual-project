package cn.gyw.individual.commons.exceptions;

import cn.gyw.individual.commons.enums.BaseEnum;
import cn.gyw.individual.commons.enums.CodeEnum;

/**
 * 业务异常断言
 */
public interface BusinessExceptionAssert extends BaseEnum<CodeEnum>, ExceptionAssert {

    @Override
    default BaseException newException(String message) {
        return new BusinessException(this, message);
    }

    @Override
    default BaseException newException(Throwable t, String message) {
        return new BusinessException(this, message, t);
    }
}
