package cn.gyw.backend.asset.domain.assetrecord;

import javax.persistence.AttributeConverter;

public class InOutBizTypeConverter implements AttributeConverter<InOutBizType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InOutBizType inOutBizType) {
        return inOutBizType.getCode();
    }

    @Override
    public InOutBizType convertToEntityAttribute(Integer code) {
        return InOutBizType.of(code).orElse(null);
    }
}
