package cn.gyw.backend.infrastructure.security.authentication.admin.password;

import cn.gyw.backend.infrastructure.config.SecurityProperties;
import cn.gyw.backend.infrastructure.constants.AuthConstants;
import cn.gyw.backend.infrastructure.security.LoginUserType;
import cn.gyw.backend.system.domain.admin.AdminUser;
import cn.gyw.backend.system.domain.admin.repository.AdminUserRepository;
import cn.gyw.individual.starters.security.base.BaseAuthenticationProvider;
import cn.gyw.individual.starters.security.base.LoginSuccessToken;
import cn.gyw.individual.starters.security.config.AuthErrorMsg;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

/**
 * @date 2023/5/23
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminPasswordAuthenticationProvider extends BaseAuthenticationProvider implements AuthenticationProvider {

    private final AdminUserRepository adminUserRepository;

    private final SecurityProperties securityProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminUsernamePasswordToken token = (AdminUsernamePasswordToken) authentication;
        if (StringUtils.isEmpty(token.getUsername()) || StringUtils.isEmpty(token.getPassword())) {
            throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
        }
        Optional<AdminUser> adminUser = adminUserRepository.findByPhone(token.getUsername());
        if (!adminUser.isPresent()) {
            throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean matches = bCryptPasswordEncoder.matches(token.getPassword(),
                    adminUser.get().getPassword());
            if (matches) {
                Map<String, Object> jwtInfo = Maps.newHashMap();
                jwtInfo.put(AuthConstants.ID, adminUser.get().getId());
                jwtInfo.put(AuthConstants.LOGIN_USER_TYPE, LoginUserType.ADMIN_USER.getCode());
                String jwtToken = JWTUtil.createToken(jwtInfo,
                        securityProperties.getSecret().getBytes(StandardCharsets.UTF_8));
                return new LoginSuccessToken(jwtToken, adminUser.get().getUsername());
            } else {
                throw new BadCredentialsException(AuthErrorMsg.passwordIncorrect.getName());
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AdminUsernamePasswordToken.class.isAssignableFrom(aClass);
    }
}
