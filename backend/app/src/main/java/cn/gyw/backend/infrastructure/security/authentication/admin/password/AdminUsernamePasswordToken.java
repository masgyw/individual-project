package cn.gyw.backend.infrastructure.security.authentication.admin.password;

import cn.gyw.individual.starters.security.base.BaseUsernamePasswordToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @date 2023/5/23
 */
public class AdminUsernamePasswordToken extends BaseUsernamePasswordToken {

    public AdminUsernamePasswordToken(Collection<? extends GrantedAuthority> authorities, String username, String password) {
        super(authorities, username, password);
    }
}
