package cn.gyw.backend.message.domain.messagetemplate;

import javax.persistence.AttributeConverter;

public class TemplateTypeConverter implements AttributeConverter<TemplateType, Integer> {

  @Override
  public Integer convertToDatabaseColumn(TemplateType templateType) {
    return templateType.getCode();
  }

  @Override
  public TemplateType convertToEntityAttribute(Integer code) {
    return TemplateType.of(code).orElse(null);
  }
}
