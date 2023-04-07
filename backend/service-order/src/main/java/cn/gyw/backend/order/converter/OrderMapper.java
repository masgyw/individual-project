package cn.gyw.backend.order.converter;

import cn.gyw.backend.order.domain.objectsku.SkuType;
import cn.gyw.backend.order.domain.trade.order.OrderState;
import cn.gyw.backend.order.domain.trade.order.OrderType;
import cn.gyw.backend.order.domain.trade.orderlifecycle.OrderOperateType;
import cn.gyw.individual.commons.enums.AccountType;

/**
 * @date 2023/4/7
 */
public class OrderMapper {

    public Integer skuType2Int(SkuType skuType) {
        return skuType.getCode();
    }

    public SkuType int2SkuType(Integer code) {
        return SkuType.of(code).orElse(SkuType.SINGLE);
    }

    public Integer orderType2Int(OrderType orderType) {
        return orderType.getCode();
    }

    public OrderType int2OrderType(Integer code) {
        return OrderType.of(code).orElse(OrderType.CHARGE);
    }

    public Integer accountType2Int(AccountType accountType) {
        return accountType.getCode();
    }

    public AccountType int2AccountType(Integer code) {
        return AccountType.of(code).orElse(AccountType.PERSONAL);
    }


    public Integer opType2Int(OrderOperateType type) {
        return type.getCode();
    }

    public OrderOperateType int2OpType(Integer code) {
        return OrderOperateType.of(code).orElse(OrderOperateType.AUTH_SUCCESS);
    }

    public Integer status2OrderState(OrderState state) {
        return state.getCode();
    }

    public OrderState state2Int(Integer code) {
        return OrderState.of(code).orElse(OrderState.WAIT_PAY);
    }
}
