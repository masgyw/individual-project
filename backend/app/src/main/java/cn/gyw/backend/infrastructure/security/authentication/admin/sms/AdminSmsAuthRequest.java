package cn.gyw.backend.infrastructure.security.authentication.admin.sms;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @date 2023/5/23
 */
@Data
@Schema
public class AdminSmsAuthRequest {

    private String phone;

    private String verifyCode;

}
