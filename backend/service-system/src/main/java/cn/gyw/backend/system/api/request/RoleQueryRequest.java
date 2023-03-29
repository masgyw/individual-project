// ---Auto Generated ---
package cn.gyw.backend.system.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class RoleQueryRequest implements Request {
  @Schema(
      title = "角色名称"
  )
  private String name;

  @Schema(
      title = "平台Id"
  )
  private Long platformId;

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
}
