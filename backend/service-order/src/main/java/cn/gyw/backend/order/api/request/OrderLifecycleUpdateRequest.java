// ---Auto Generated ---
package cn.gyw.backend.order.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;

@Schema
public class OrderLifecycleUpdateRequest implements Request {
  @Schema(
      title = "flowNo"
  )
  private Long flowNo;

  @Schema(
      title = "操作类型"
  )
  private Integer operateType;

  @Schema(
      title = "remark"
  )
  private String remark;

  @Schema(
      title = "operateUser"
  )
  private String operateUser;

  private Long id;

  public Long getFlowNo() {
    return flowNo;
  }

  public void setFlowNo(Long flowNo) {
    this.flowNo = flowNo;
  }

  public Integer getOperateType() {
    return operateType;
  }

  public void setOperateType(Integer operateType) {
    this.operateType = operateType;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getOperateUser() {
    return operateUser;
  }

  public void setOperateUser(String operateUser) {
    this.operateUser = operateUser;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
