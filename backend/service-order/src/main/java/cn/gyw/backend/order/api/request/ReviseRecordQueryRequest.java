// ---Auto Generated ---
package cn.gyw.backend.order.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class ReviseRecordQueryRequest implements Request {
  @Schema(
      title = "唯一流水"
  )
  private Long flowNo;

  @Schema(
      title = "表名"
  )
  private String tableName;

  public Long getFlowNo() {
    return flowNo;
  }

  public void setFlowNo(Long flowNo) {
    this.flowNo = flowNo;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }
}
