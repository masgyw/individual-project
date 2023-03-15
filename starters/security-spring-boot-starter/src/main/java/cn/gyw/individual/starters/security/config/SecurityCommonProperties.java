package cn.gyw.individual.starters.security.config;

import cn.gyw.individual.commons.annotations.FieldDesc;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "cn.gyw.security.urls")
public class SecurityCommonProperties {

    @FieldDesc(name = "不需要权限的链接地址集合")
    private List<String> unAuthUrls = Lists.newArrayList();
}
