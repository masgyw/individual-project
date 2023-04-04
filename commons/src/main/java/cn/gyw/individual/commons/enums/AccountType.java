package cn.gyw.individual.commons.enums;

import java.util.Optional;

/**
 * 账户类型
 */
public enum AccountType implements BaseEnum<AccountType> {

    PERSONAL(1, "个人"),
    CORP(2, "企业");

    AccountType(Integer code, String name) {
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

    public static Optional<AccountType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(AccountType.class, code));
    }
}
