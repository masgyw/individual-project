package cn.gyw.backend.infrastructure.security;

import lombok.Getter;

/**
 * @date 2023/5/23
 */
@Getter
public enum AuthUrlEnum {

    ADMIN_PASS("/auth/admin/pass", "后台用户密码登录"),
    ADMIN_SMS("/auth/admin/sms", "后台用户短信验证码登录");

    AuthUrlEnum(String url, String name) {
        this.url = url;
        this.name = name;
    }

    private final String url;

    private final String name;
}
