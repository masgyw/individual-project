package cn.gyw.individual.commons.mapper;

import cn.gyw.individual.commons.enums.ValidStatus;

public class GenericEnumMapper {

    public Integer asInteger(ValidStatus status) {
        return status.getCode();
    }

    public ValidStatus asValidStatus(Integer code) {
        return ValidStatus.of(code).orElse(ValidStatus.INVALID);
    }
}