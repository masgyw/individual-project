package cn.gyw.backend.message.domain.messagerecord;

import javax.persistence.AttributeConverter;

public class NoticeTypeConverter implements AttributeConverter<NoticeType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(NoticeType noticeType) {
        return noticeType.getCode();
    }

    @Override
    public NoticeType convertToEntityAttribute(Integer code) {
        return NoticeType.of(code).orElse(null);
    }
}
