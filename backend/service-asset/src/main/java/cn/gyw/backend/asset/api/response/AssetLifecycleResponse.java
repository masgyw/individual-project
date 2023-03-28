// ---Auto Generated ---
package cn.gyw.backend.asset.api.response;

import cn.gyw.backend.asset.domain.assetrecord.InOutBizType;
import cn.gyw.backend.asset.domain.assetrecord.InOutType;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class AssetLifecycleResponse extends AbstractResponse {
  @Schema(
      title = "资产名称"
  )
  private String name;

  @Schema(
      title = "资产Id"
  )
  private Long assetsId;

  @Schema(
      title = "skuId"
  )
  private Long skuId;

  @Schema(
      title = "唯一编码"
  )
  private String uniqueCode;

  @Schema(
      title = "备注"
  )
  private String remark;

  @Schema(
      title = "仓库名称"
  )
  private String houseName;

  @Schema(
      title = "仓库id"
  )
  private Long houseId;

  @Schema(
      title = "出入库业务类型"
  )
  private InOutBizType inOutBizType;

  @Schema(
      title = "出入类型"
  )
  private InOutType inOutType;

  @Schema(
      title = "操作人"
  )
  private String operateUser;

  @Schema(
      title = "唯一批次号"
  )
  private String genBatchNo;

  @Schema(
      title = "批次号"
  )
  private String batchNo;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getAssetsId() {
    return assetsId;
  }

  public void setAssetsId(Long assetsId) {
    this.assetsId = assetsId;
  }

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public String getUniqueCode() {
    return uniqueCode;
  }

  public void setUniqueCode(String uniqueCode) {
    this.uniqueCode = uniqueCode;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getHouseName() {
    return houseName;
  }

  public void setHouseName(String houseName) {
    this.houseName = houseName;
  }

  public Long getHouseId() {
    return houseId;
  }

  public void setHouseId(Long houseId) {
    this.houseId = houseId;
  }

  public InOutBizType getInOutBizType() {
    return inOutBizType;
  }

  public void setInOutBizType(InOutBizType inOutBizType) {
    this.inOutBizType = inOutBizType;
  }

  public InOutType getInOutType() {
    return inOutType;
  }

  public void setInOutType(InOutType inOutType) {
    this.inOutType = inOutType;
  }

  public String getOperateUser() {
    return operateUser;
  }

  public void setOperateUser(String operateUser) {
    this.operateUser = operateUser;
  }

  public String getGenBatchNo() {
    return genBatchNo;
  }

  public void setGenBatchNo(String genBatchNo) {
    this.genBatchNo = genBatchNo;
  }

  public String getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
