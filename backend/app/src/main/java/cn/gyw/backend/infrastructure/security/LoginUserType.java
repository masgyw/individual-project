package cn.gyw.backend.infrastructure.security;

import cn.gyw.individual.commons.enums.BaseEnum;

import java.util.Optional;

/**
 * @date 2023/7/25
 */
public enum LoginUserType implements BaseEnum<LoginUserType> {
    ADMIN_USER(1, "后端用户"),
    CUSTOMER(2, "前端用户");

    private Integer code;
    private String name;

    LoginUserType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<LoginUserType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(LoginUserType.class, code));
    }
}
