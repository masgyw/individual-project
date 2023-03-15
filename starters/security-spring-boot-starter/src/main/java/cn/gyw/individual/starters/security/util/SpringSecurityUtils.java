package cn.gyw.individual.starters.security.util;

import cn.gyw.individual.starters.security.base.BaseJwtUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SpringSecurityUtils {
    /**
     * 获取当前用户名
     * @return 用户名
     */
    public static String getCurrentUsername() {
        String temp = "";
        try {
            BaseJwtUser jwtUser = getJwtUser();
            if (Objects.nonNull(jwtUser)) {
                return jwtUser.getUsername();
            } else {
                temp = "-";
            }
        } catch (Exception e) {
            temp = "";
        }
        return temp;
    }

    public static BaseJwtUser getJwtUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getClass().isAssignableFrom(AnonymousAuthenticationToken.class)) {
            return null;
        } else {
            BaseJwtUser info = (BaseJwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return info;
        }

    }
}
