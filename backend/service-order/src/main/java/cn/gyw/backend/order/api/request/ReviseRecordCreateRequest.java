// ---Auto Generated ---
package cn.gyw.backend.order.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class ReviseRecordCreateRequest implements Request {
  @Schema(
      title = "操作人"
  )
  private String operateUser;

  @Schema(
      title = "唯一流水"
  )
  private Long flowNo;

  @Schema(
      title = "修订前"
  )
  private String reviseBefore;

  @Schema(
      title = "修订后"
  )
  private String reviseAfter;

  @Schema(
      title = "差别"
  )
  private String diff;

  @Schema(
      title = "表名"
  )
  private String tableName;

  @Schema(
      title = "修订原因"
  )
  private String reviseReason;

  public String getOperateUser() {
    return operateUser;
  }

  public void setOperateUser(String operateUser) {
    this.operateUser = operateUser;
  }

  public Long getFlowNo() {
    return flowNo;
  }

  public void setFlowNo(Long flowNo) {
    this.flowNo = flowNo;
  }

  public String getReviseBefore() {
    return reviseBefore;
  }

  public void setReviseBefore(String reviseBefore) {
    this.reviseBefore = reviseBefore;
  }

  public String getReviseAfter() {
    return reviseAfter;
  }

  public void setReviseAfter(String reviseAfter) {
    this.reviseAfter = reviseAfter;
  }

  public String getDiff() {
    return diff;
  }

  public void setDiff(String diff) {
    this.diff = diff;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getReviseReason() {
    return reviseReason;
  }

  public void setReviseReason(String reviseReason) {
    this.reviseReason = reviseReason;
  }
}
