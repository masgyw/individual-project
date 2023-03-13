package cn.gyw.individual.backend.app;

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
@ConfigurationPropertiesScan(basePackages = "cn.gyw.individual.backend")
@SpringBootApplication(scanBasePackages = {"cn.gyw.individual.backend"})
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                // 加载spring版本
                .main(SpringVersion.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
