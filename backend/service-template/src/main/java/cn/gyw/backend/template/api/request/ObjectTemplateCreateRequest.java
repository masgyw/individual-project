// ---Auto Generated ---
package cn.gyw.backend.template.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class ObjectTemplateCreateRequest implements Request {
  @Schema(
      title = "模板名称"
  )
  private String name;

  @Schema(
      title = "模板编码"
  )
  private String code;

  @Schema(
      title = "创建人"
  )
  private String createUser;

  @Schema(
      title = "模板code"
  )
  private String categoryCode;

  @Schema(
      title = "模板id"
  )
  private Long categoryId;

  @Schema(
      title = "描述信息"
  )
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public String getCategoryCode() {
    return categoryCode;
  }

  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
