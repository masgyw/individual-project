// ---Auto Generated ---
package cn.gyw.backend.system.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class RoleResponse extends AbstractResponse {
  @Schema(
      title = "角色编码"
  )
  private String role;

  @Schema(
      title = "角色名称"
  )
  private String name;

  @Schema(
      title = "平台Id"
  )
  private Long platformId;

  @Schema(
      title = "备注"
  )
  private String remark;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getPlatformId() {
    return platformId;
  }

  public void setPlatformId(Long platformId) {
    this.platformId = platformId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
