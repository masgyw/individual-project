package cn.gyw.individual.commons.pay;

import java.math.BigDecimal;

/**
 * 抽象支付项
 */
public abstract class AbstractPayItem implements PayItem {

    private BigDecimal money;

    private PayType payType;

    private PayGroup payGroup;

    public AbstractPayItem(BigDecimal money, PayType payType, PayGroup payGroup) {
        this.money = money;
        this.payType = payType;
        this.payGroup = payGroup;
    }

    @Override
    public BigDecimal getMoney() {
        return money;
    }

    @Override
    public PayGroup getPayGroup() {
        return payGroup;
    }

    @Override
    public PayType getPayType() {
        return payType;
    }
}
