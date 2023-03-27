package cn.gyw.individual.starters.security.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "cn.gyw.security")
public class LoginConfigProperties {

    private Long expired = 30L;

    private boolean enable;

}
