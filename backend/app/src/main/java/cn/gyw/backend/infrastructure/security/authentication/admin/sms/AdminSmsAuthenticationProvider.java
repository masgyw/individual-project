package cn.gyw.backend.infrastructure.security.authentication.admin.sms;

import cn.gyw.backend.infrastructure.config.SecurityProperties;
import cn.gyw.backend.infrastructure.constants.AuthErrorCode;
import cn.gyw.backend.system.domain.admin.AdminUser;
import cn.gyw.backend.system.domain.admin.repository.AdminUserRepository;
import cn.gyw.individual.starters.security.base.BaseAuthenticationProvider;
import cn.gyw.individual.starters.security.base.LoginSuccessToken;
import cn.gyw.individual.starters.security.config.AuthErrorMsg;
import cn.gyw.individual.starters.security.exception.CustomAuthenticationException;
import cn.hutool.jwt.JWTUtil;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @date 2023/5/23
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AdminSmsAuthenticationProvider extends BaseAuthenticationProvider implements AuthenticationProvider {

    private final AdminUserRepository adminUserRepository;

    private final SecurityProperties securityProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminSmsToken token = (AdminSmsToken) authentication;
        if (StringUtils.isEmpty(token.getPhone()) || StringUtils.isEmpty(token.getVerifyCode())) {
            throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
        }
        Optional<AdminUser> adminUser = adminUserRepository.findByPhone(token.getPhone());
        if (!adminUser.isPresent()) {
            throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
        } else {
            //验证码校验逻辑
            if (Objects.equals(token.getVerifyCode(), "666666")) {
                Map<String, Object> jwtInfo = Maps.newHashMap();
                jwtInfo.put("id", adminUser.get().getId());
                jwtInfo.put("type", "admin");
                String jwtToken = JWTUtil.createToken(jwtInfo,
                        securityProperties.getSecret().getBytes(StandardCharsets.UTF_8));
                return new LoginSuccessToken(jwtToken, adminUser.get().getUsername());
            } else {
                throw new CustomAuthenticationException(AuthErrorCode.VERIFY_CODE_INCORRECT.getCode(), AuthErrorCode.VERIFY_CODE_INCORRECT.getName());
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AdminSmsToken.class.isAssignableFrom(aClass);
    }
}
