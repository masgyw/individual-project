// ---Auto Generated ---
package cn.gyw.backend.message.api.response;

import cn.gyw.backend.message.domain.messagetemplate.TemplateType;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.String;

@Schema
public class MessageTemplateResponse extends AbstractResponse {
  @Schema(
      title = "模板编码"
  )
  private String templateCode;

  @Schema(
      title = "名称"
  )
  private String name;

  @Schema(
      title = "模板"
  )
  private String template;

  @Schema(
      title = "模板类型"
  )
  private TemplateType templateType;

  @Schema(
      title = "描述信息"
  )
  private String description;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  public String getTemplateCode() {
    return templateCode;
  }

  public void setTemplateCode(String templateCode) {
    this.templateCode = templateCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public TemplateType getTemplateType() {
    return templateType;
  }

  public void setTemplateType(TemplateType templateType) {
    this.templateType = templateType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
