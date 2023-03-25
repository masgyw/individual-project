// ---Auto Generated ---
package cn.gyw.backend.template.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import cn.gyw.individual.commons.model.DictValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.util.List;

@Schema
public class TemplateItemResponse extends AbstractResponse {
  @Schema(
      title = "名称"
  )
  private String name;

  @Schema(
      title = "输入类型"
  )
  private Integer inputType;

  @Schema(
      title = "占位符"
  )
  private String placeholder;

  @Schema(
      title = "编码"
  )
  private String code;

  @Schema(
      title = "创建人"
  )
  private String createUser;

  @Schema(
      title = "排序号"
  )
  private BigDecimal sortNum;

  @Schema(
      title = "备注"
  )
  private String remark;

  @Schema(
      title = "是否必填"
  )
  private Integer requireFlag;

  @Schema(
      title = "是否需要审批"
  )
  private Integer auditFlag;

  @Schema(
      title = "关联字典编码"
  )
  private String selectCode;

  @Schema(
      title = "关联字典的id"
  )
  private Long dictId;

  @Schema(
      title = "开发扩展字段"
  )
  private List<DictValue> extList;

  @Schema(
      title = "categoryId"
  )
  private Long categoryId;

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

  public Integer getInputType() {
    return inputType;
  }

  public void setInputType(Integer inputType) {
    this.inputType = inputType;
  }

  public String getPlaceholder() {
    return placeholder;
  }

  public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public BigDecimal getSortNum() {
    return sortNum;
  }

  public void setSortNum(BigDecimal sortNum) {
    this.sortNum = sortNum;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getRequireFlag() {
    return requireFlag;
  }

  public void setRequireFlag(Integer requireFlag) {
    this.requireFlag = requireFlag;
  }

  public Integer getAuditFlag() {
    return auditFlag;
  }

  public void setAuditFlag(Integer auditFlag) {
    this.auditFlag = auditFlag;
  }

  public String getSelectCode() {
    return selectCode;
  }

  public void setSelectCode(String selectCode) {
    this.selectCode = selectCode;
  }

  public Long getDictId() {
    return dictId;
  }

  public void setDictId(Long dictId) {
    this.dictId = dictId;
  }

  public List<DictValue> getExtList() {
    return extList;
  }

  public void setExtList(List<DictValue> extList) {
    this.extList = extList;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
