// ---Auto Generated ---
package cn.gyw.backend.order.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;

@Schema
public class OrderLifecycleResponse extends AbstractResponse {
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

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

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

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
