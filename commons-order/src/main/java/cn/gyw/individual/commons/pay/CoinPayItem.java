package cn.gyw.individual.commons.pay;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CoinPayItem extends AbstractPayItem {

    public CoinPayItem(BigDecimal money) {
        super(money, PayType.COIN, PayGroup.VIRTUAL_PROPERTY);
    }

    /**
     * 来源
     */
    private String source;
}
