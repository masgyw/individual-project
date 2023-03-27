package cn.gyw.individual.starters.security.base;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 基础session 数据 通过token 直接能换取的信息
 */
@Data
public abstract class BaseJwtUser implements Serializable {

    private Long userId;

    private String username;

    private Map<String, String> extInfo;

    private Collection<? extends GrantedAuthority> authorities;
}
