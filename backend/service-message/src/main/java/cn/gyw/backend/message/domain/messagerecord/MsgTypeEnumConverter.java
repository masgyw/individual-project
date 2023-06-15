package cn.gyw.backend.message.domain.messagerecord;

import javax.persistence.AttributeConverter;

public class MsgTypeEnumConverter implements AttributeConverter<MsgTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MsgTypeEnum msgTypeEnum) {
        return msgTypeEnum.getCode();
    }

    @Override
    public MsgTypeEnum convertToEntityAttribute(Integer code) {
        return MsgTypeEnum.of(code).orElse(null);
    }
}