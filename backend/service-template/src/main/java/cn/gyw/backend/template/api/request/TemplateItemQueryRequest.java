// ---Auto Generated ---
package cn.gyw.backend.template.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.String;

@Schema
public class TemplateItemQueryRequest implements Request {
  @Schema(
      title = "名称"
  )
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
