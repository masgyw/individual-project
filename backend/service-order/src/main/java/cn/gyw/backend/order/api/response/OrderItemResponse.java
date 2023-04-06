// ---Auto Generated ---
package cn.gyw.backend.order.api.response;

import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;

@Schema
public class OrderItemResponse extends AbstractResponse {
  @Schema(
      title = "订单id"
  )
  private Long orderId;

  @Schema(
      title = "唯一流水号"
  )
  private Long flowNo;

  @Schema(
      title = "真实金额"
  )
  private BigDecimal realAmount;

  @Schema(
      title = "计量数量"
  )
  private BigDecimal itemCount;

  @Schema(
      title = "skuId"
  )
  private String skuId;

  @Schema(
      title = "商品名称"
  )
  private String goodsName;

  @Schema(
      title = "费用描述"
  )
  private String feeRemark;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getFlowNo() {
    return flowNo;
  }

  public void setFlowNo(Long flowNo) {
    this.flowNo = flowNo;
  }

  public BigDecimal getRealAmount() {
    return realAmount;
  }

  public void setRealAmount(BigDecimal realAmount) {
    this.realAmount = realAmount;
  }

  public BigDecimal getItemCount() {
    return itemCount;
  }

  public void setItemCount(BigDecimal itemCount) {
    this.itemCount = itemCount;
  }

  public String getSkuId() {
    return skuId;
  }

  public void setSkuId(String skuId) {
    this.skuId = skuId;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public String getFeeRemark() {
    return feeRemark;
  }

  public void setFeeRemark(String feeRemark) {
    this.feeRemark = feeRemark;
  }
}
