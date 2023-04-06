// ---Auto Generated ---
package cn.gyw.backend.order.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class SkuAttributeResponse extends AbstractResponse {
  @Schema(
      title = "skuId"
  )
  private Long skuId;

  @Schema(
      title = "模板项id"
  )
  private Long itemId;

  @Schema(
      title = "编码"
  )
  private String code;

  @Schema(
      title = "值"
  )
  private String value;

  @Schema(
      title = "标签"
  )
  private String label;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
