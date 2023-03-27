package cn.gyw.backend.asset.domain.assetrecord;

import javax.persistence.AttributeConverter;

public class InOutTypeConverter implements AttributeConverter<InOutType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InOutType inOutType) {
        return inOutType.getCode();
    }

    @Override
    public InOutType convertToEntityAttribute(Integer code) {
        return InOutType.of(code).orElse(null);
    }
}