package cn.gyw.backend.order.domain.trade.order;

import cn.gyw.individual.commons.enums.BaseEnum;

import java.util.Optional;

public enum OrderState implements BaseEnum<OrderState> {

    WAIT_PAY(0, "待支付"),
    PAY(1, "支付中"),
    PAY_SUCCESS(2, "支付完成"),
    PAY_FAILED(3, "支付失败"),
    CANCEL(4, "已取消");

    OrderState(Integer code, String name) {
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

    public static Optional<OrderState> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(OrderState.class, code));
    }

}
