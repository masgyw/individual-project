package cn.gyw.backend.infrastructure.security.authorization;

import cn.gyw.backend.infrastructure.config.SecurityProperties;
import cn.gyw.backend.infrastructure.constants.AuthConstants;
import cn.gyw.backend.infrastructure.security.LoginUserType;
import cn.gyw.backend.infrastructure.security.SystemJwtUser;
import cn.gyw.backend.system.domain.admin.service.IAdminUserService;
import cn.gyw.backend.system.domain.admin.vo.AdminUserVO;
import cn.gyw.individual.starters.security.base.BaseJwtUser;
import cn.gyw.individual.starters.security.base.extension.DummyJwtUser;
import cn.gyw.individual.starters.security.base.extension.UserContextAware;
import cn.gyw.individual.starters.security.config.AuthErrorMsg;
import cn.gyw.individual.starters.security.exception.CustomAuthenticationException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @date 2023/7/25
 */
@Component
@RequiredArgsConstructor
public class AdminUserContextAware implements UserContextAware {

    private final SecurityProperties securityProperties;

    private final IAdminUserService adminUserService;

    @Override
    public BaseJwtUser processToken(String token) {
        boolean pass = JWTUtil.verify(token, securityProperties.getSecret().getBytes(StandardCharsets.UTF_8));
        if (!pass) {
            throw new CustomAuthenticationException(AuthErrorMsg.tokenIllegal.getCode(), AuthErrorMsg.tokenIllegal.getName());
        }
        JWT jwt = JWTUtil.parseToken(token);
        Long userId = Long.valueOf(jwt.getPayload().getClaim(AuthConstants.ID).toString());
        LoginUserType type = LoginUserType.of(Integer.valueOf(jwt.getPayload().getClaim(AuthConstants.LOGIN_USER_TYPE).toString())).get();
        if (Objects.equals(LoginUserType.ADMIN_USER, type)) {
            //如果是后台用户，查询用户信息，并授权
            AdminUserVO vo = adminUserService.findById(userId);
            SystemJwtUser systemJwtUser = new SystemJwtUser();
            systemJwtUser.setUsername(vo.getUsername());
            List<GrantedAuthority> authorities = new ArrayList<>();
            // TODO 系统获取角色
            GrantedAuthority role = new SimpleGrantedAuthority("ROLE_ADMIN");
            authorities.add(role);

            systemJwtUser.setAuthorities(authorities);

            return systemJwtUser;
        }
        return new DummyJwtUser();
    }
}
