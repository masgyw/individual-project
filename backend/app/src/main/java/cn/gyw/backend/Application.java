package cn.gyw.backend;

import cn.gyw.individual.starters.security.SecurityAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.core.SpringVersion;

/**
 * 启动类
 *
 * @date 2023/2/23
 */
@ConfigurationPropertiesScan(basePackages = "cn.gyw.backend")
@SpringBootApplication(scanBasePackages = {"cn.gyw.backend"}, exclude = {SecurityAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                // 加载spring版本
                .main(SpringVersion.class)
                .bannerMode(Banner.Mode.LOG)
                .run(args);
    }
}
