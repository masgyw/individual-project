package cn.gyw.individual.starters.security.config;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "cn.gyw.security.urls")
public class SecurityCommonProperties {

    private List<String> unAuthUrls = Lists.newArrayList();
}
