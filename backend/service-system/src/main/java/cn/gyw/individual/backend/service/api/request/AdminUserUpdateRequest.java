// ---Auto Generated ---
package cn.gyw.individual.backend.service.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class AdminUserUpdateRequest implements Request {
  @Schema(
      title = "手机号"
  )
  private String phone;

  @Schema(
      title = "密码"
  )
  private String password;

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

  private Long id;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
