package cn.gyw.individual.commons.pay;

import cn.gyw.individual.commons.enums.BaseEnum;

import java.util.Optional;

/**
 * 支付类型
 */
public enum PayType implements BaseEnum<PayType> {

    WECHAT(1, "微信支付"),
    ALIPAY(2, "支付宝"),
    COIN(3, "虚拟币"),
    ACTIVITY(4, "活动");

    PayType(Integer code, String name) {
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

    public static Optional<PayType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(PayType.class, code));
    }

}