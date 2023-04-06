// ---Auto Generated ---
package cn.gyw.backend.order.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;

@Schema
public class OrderBaseQueryRequest implements Request {
  @Schema(
      title = "订单类型、订单类型创建不同的状态机"
  )
  private Integer orderType;

  public Integer getOrderType() {
    return orderType;
  }

  public void setOrderType(Integer orderType) {
    this.orderType = orderType;
  }
}
