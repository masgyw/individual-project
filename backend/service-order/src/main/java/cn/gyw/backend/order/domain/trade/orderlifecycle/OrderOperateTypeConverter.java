package cn.gyw.backend.order.domain.trade.orderlifecycle;

import javax.persistence.AttributeConverter;

public class OrderOperateTypeConverter implements AttributeConverter<OrderOperateType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderOperateType orderOperateType) {
        return orderOperateType.getCode();
    }

    @Override
    public OrderOperateType convertToEntityAttribute(Integer code) {
        return OrderOperateType.of(code).orElse(null);
    }
}
