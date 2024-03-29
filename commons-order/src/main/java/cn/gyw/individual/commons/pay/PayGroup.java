package cn.gyw.individual.commons.pay;

import cn.gyw.individual.commons.enums.BaseEnum;

import java.util.Optional;

/**
 * 支付组
 */
public enum PayGroup implements BaseEnum<PayGroup> {

    THIRD_PAY(1, "三方支付"),
    PLATFORM_PAY(2, "平台支付"),
    VIRTUAL_PROPERTY(3, "虚拟资产"),
    BANK(4, "银行卡支付"),
    COUPON(5, "优惠劵");

    PayGroup(Integer code, String name) {
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

    public static Optional<PayGroup> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(PayGroup.class, code));
    }

}
