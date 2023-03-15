package cn.gyw.individual.starters.security;

import cn.gyw.individual.starters.security.auto.SecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(prefix = "cn.gyw.security", name = "enable", havingValue = "true")
public class SecurityAutoConfiguration {

    @Configuration
    @ComponentScan(value = {"cn.gyw.individual.starters.security.base", "cn.gyw.individual.starters.security.config"})
    @Import(value = {SecurityConfig.class})
    public class AdminSecurityConfig {

    }
}
