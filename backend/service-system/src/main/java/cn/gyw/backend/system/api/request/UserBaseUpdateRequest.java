// ---Auto Generated ---
package cn.gyw.backend.system.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class UserBaseUpdateRequest implements Request {
  @Schema(
      title = "uuid"
  )
  private String uuid;

  @Schema(
      title = "手机号"
  )
  private String phone;

  private Long id;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
