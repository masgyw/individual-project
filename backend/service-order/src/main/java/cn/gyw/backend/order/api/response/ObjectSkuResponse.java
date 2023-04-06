// ---Auto Generated ---
package cn.gyw.backend.order.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;

@Schema
public class ObjectSkuResponse extends AbstractResponse {
  @Schema(
      title = "sku名称"
  )
  private String skuName;

  @Schema(
      title = "模板ID"
  )
  private Long templateId;

  @Schema(
      title = "编码"
  )
  private String code;

  @Schema(
      title = "备注"
  )
  private String remark;

  @Schema(
      title = "sku类型"
  )
  private Integer skuType;

  @Schema(
      title = "税务分类编码"
  )
  private String taxCategoryNo;

  @Schema(
      title = "计量单位"
  )
  private String measureUnit;

  @Schema(
      title = "子sku ID list"
  )
  private List<Long> children;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  public Long getTemplateId() {
    return templateId;
  }

  public void setTemplateId(Long templateId) {
    this.templateId = templateId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getSkuType() {
    return skuType;
  }

  public void setSkuType(Integer skuType) {
    this.skuType = skuType;
  }

  public String getTaxCategoryNo() {
    return taxCategoryNo;
  }

  public void setTaxCategoryNo(String taxCategoryNo) {
    this.taxCategoryNo = taxCategoryNo;
  }

  public String getMeasureUnit() {
    return measureUnit;
  }

  public void setMeasureUnit(String measureUnit) {
    this.measureUnit = measureUnit;
  }

  public List<Long> getChildren() {
    return children;
  }

  public void setChildren(List<Long> children) {
    this.children = children;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
