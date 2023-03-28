// ---Auto Generated ---
package cn.gyw.backend.asset.api.request;

import cn.gyw.backend.asset.domain.assetrecord.InOutBizType;
import cn.gyw.backend.asset.domain.assetrecord.InOutType;
import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.String;

@Schema
public class AssetInOutRecordCreateRequest implements Request {
  @Schema(
      title = "手动录入批次号,仅用于展示"
  )
  private String batchNo;

  @Schema(
      title = "自动生成的批次号，防止重复"
  )
  private String genBatchNo;

  @Schema(
      title = "出入库业务类型"
  )
  private InOutBizType inOutBizType;

  @Schema(
      title = "操作人"
  )
  private String operateUser;

  @Schema(
      title = "出入类型"
  )
  private InOutType inOutType;

  @Schema(
      title = "总数"
  )
  private Integer totalCount;

  public String getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }

  public String getGenBatchNo() {
    return genBatchNo;
  }

  public void setGenBatchNo(String genBatchNo) {
    this.genBatchNo = genBatchNo;
  }

  public InOutBizType getInOutBizType() {
    return inOutBizType;
  }

  public void setInOutBizType(InOutBizType inOutBizType) {
    this.inOutBizType = inOutBizType;
  }

  public String getOperateUser() {
    return operateUser;
  }

  public void setOperateUser(String operateUser) {
    this.operateUser = operateUser;
  }

  public InOutType getInOutType() {
    return inOutType;
  }

  public void setInOutType(InOutType inOutType) {
    this.inOutType = inOutType;
  }

  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }
}
