package cn.gyw.individual.commons.pay;

import java.math.BigDecimal;

public interface PayItem {

    BigDecimal getMoney();

    PayGroup getPayGroup();

    PayType getPayType();

}
