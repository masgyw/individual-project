// ---Auto Generated ---
package cn.gyw.backend.template.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class ObjectTemplateResponse extends AbstractResponse {
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

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

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

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
