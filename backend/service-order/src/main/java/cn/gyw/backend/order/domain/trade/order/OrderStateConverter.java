package cn.gyw.backend.order.domain.trade.order;

import javax.persistence.AttributeConverter;

public class OrderStateConverter implements AttributeConverter<OrderState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderState orderState) {
        return orderState.getCode();
    }

    @Override
    public OrderState convertToEntityAttribute(Integer code) {
        return OrderState.of(code).orElse(null);
    }
}
