package cn.gyw.backend.infrastructure.constants;

import cn.gyw.individual.commons.enums.BaseEnum;

import java.util.Optional;

/**
 * @date 2023/5/23
 */
public enum AuthErrorCode implements BaseEnum<AuthErrorCode> {

    VERIFY_CODE_INCORRECT(1501001, "验证码错误");

    AuthErrorCode(Integer code, String name) {
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

    public static Optional<AuthErrorCode> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(AuthErrorCode.class, code));
    }

}