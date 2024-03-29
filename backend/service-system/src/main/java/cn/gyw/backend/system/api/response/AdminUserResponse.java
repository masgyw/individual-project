// ---Auto Generated ---
package cn.gyw.backend.system.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class AdminUserResponse extends AbstractResponse {
  @Schema(
      title = "手机号"
  )
  private String phone;

  @Schema(
      title = "用户名"
  )
  private String username;

  @Schema(
      title = "部门ID"
  )
  private Long departmentId;

  @Schema(
      title = "额外信息"
  )
  private String extInfo;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public String getExtInfo() {
    return extInfo;
  }

  public void setExtInfo(String extInfo) {
    this.extInfo = extInfo;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
