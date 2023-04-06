package cn.gyw.backend.order.domain.trade.order;

import cn.gyw.individual.commons.enums.BaseEnum;

import java.util.Optional;

public enum OrderType implements BaseEnum<OrderType> {
    /**
     * 订单类型 -> 业务扩展点
     */
    CHARGE(1, "充电"),
    PARK(2, "停车"),
    SHOP(3, "商城"),
    ;

    OrderType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<OrderType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(OrderType.class, code));
    }

}
