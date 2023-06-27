// ---Auto Generated ---
package cn.gyw.backend.message.api.request;

import cn.gyw.backend.message.domain.messagetemplate.TemplateType;
import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.String;

@Schema
public class MessageTemplateCreateRequest implements Request {
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
}
