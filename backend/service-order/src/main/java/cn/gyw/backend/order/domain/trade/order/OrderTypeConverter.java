package cn.gyw.backend.order.domain.trade.order;

import javax.persistence.AttributeConverter;

public class OrderTypeConverter implements AttributeConverter<OrderType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderType orderType) {
        return orderType.getCode();
    }

    @Override
    public OrderType convertToEntityAttribute(Integer code) {
        return OrderType.of(code).orElse(null);
    }
}
