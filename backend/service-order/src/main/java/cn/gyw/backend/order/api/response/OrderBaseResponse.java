// ---Auto Generated ---
package cn.gyw.backend.order.api.response;

import cn.gyw.individual.commons.enums.AccountType;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import cn.gyw.individual.commons.model.DictValue;
import cn.gyw.individual.commons.pay.PayItem;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.math.BigDecimal;
import java.util.List;

@Schema
public class OrderBaseResponse extends AbstractResponse {
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
      title = "支付详情"
  )
  private List<PayItem> payList;

  @Schema(
      title = "待支付金额"
  )
  private BigDecimal waitPay;

  @Schema(
      title = "支付时间"
  )
  private Long payTime;

  @Schema(
      title = "订单状态"
  )
  private Integer orderState;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  @Schema(
      title = "订单信息"
  )
  private List<DictValue> attrs;

  @Schema(
      title = "是否开票"
  )
  private ValidStatus invoiceFlag;

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

  public List<PayItem> getPayList() {
    return payList;
  }

  public void setPayList(List<PayItem> payList) {
    this.payList = payList;
  }

  public BigDecimal getWaitPay() {
    return waitPay;
  }

  public void setWaitPay(BigDecimal waitPay) {
    this.waitPay = waitPay;
  }

  public Long getPayTime() {
    return payTime;
  }

  public void setPayTime(Long payTime) {
    this.payTime = payTime;
  }

  public Integer getOrderState() {
    return orderState;
  }

  public void setOrderState(Integer orderState) {
    this.orderState = orderState;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }

  public List<DictValue> getAttrs() {
    return attrs;
  }

  public void setAttrs(List<DictValue> attrs) {
    this.attrs = attrs;
  }

  public ValidStatus getInvoiceFlag() {
    return invoiceFlag;
  }

  public void setInvoiceFlag(ValidStatus invoiceFlag) {
    this.invoiceFlag = invoiceFlag;
  }
}
