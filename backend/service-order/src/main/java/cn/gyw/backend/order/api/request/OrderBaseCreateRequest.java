// ---Auto Generated ---
package cn.gyw.backend.order.api.request;

import cn.gyw.individual.commons.enums.AccountType;
import cn.gyw.individual.commons.model.DictValue;
import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.math.BigDecimal;
import java.util.List;

@Schema
public class OrderBaseCreateRequest implements Request {
  @Schema(
      title = "唯一流水号"
  )
  private Long flowNo;

  @Schema(
      title = "订单金额"
  )
  private BigDecimal totalAmount;

  @Schema(
      title = "账号Id"
  )
  private Long accountId;

  @Schema(
      title = "账号类型"
  )
  private AccountType accountType;

  @Schema(
      title = "订单类型、订单类型创建不同的状态机"
  )
  private Integer orderType;

  @Schema(
      title = "待支付金额"
  )
  private BigDecimal waitPay;

  @Schema(
      title = "订单信息"
  )
  private List<DictValue> attrs;

  public Long getFlowNo() {
    return flowNo;
  }

  public void setFlowNo(Long flowNo) {
    this.flowNo = flowNo;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  public Integer getOrderType() {
    return orderType;
  }

  public void setOrderType(Integer orderType) {
    this.orderType = orderType;
  }

  public BigDecimal getWaitPay() {
    return waitPay;
  }

  public void setWaitPay(BigDecimal waitPay) {
    this.waitPay = waitPay;
  }

  public List<DictValue> getAttrs() {
    return attrs;
  }

  public void setAttrs(List<DictValue> attrs) {
    this.attrs = attrs;
  }
}
