package cn.gyw.backend.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @date 2023/5/23
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class SecurityProperties {

    private String secret;
}
