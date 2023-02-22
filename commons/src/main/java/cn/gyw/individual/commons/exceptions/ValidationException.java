package cn.gyw.individual.commons.exceptions;

import cn.gyw.individual.commons.validator.ValidateResult;
import lombok.Getter;

import java.util.List;

/**
 *
 */
public class ValidationException extends RuntimeException {
    @Getter
    private List<ValidateResult> result;

    public ValidationException(List<ValidateResult> list) {
        super();
        this.result = list;
    }
}
