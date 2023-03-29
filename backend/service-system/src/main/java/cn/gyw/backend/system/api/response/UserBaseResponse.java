// ---Auto Generated ---
package cn.gyw.backend.system.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.String;

@Schema
public class UserBaseResponse extends AbstractResponse {
  @Schema(
      title = "uuid"
  )
  private String uuid;

  @Schema(
      title = "手机号"
  )
  private String phone;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

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

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
