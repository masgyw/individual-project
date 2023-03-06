package cn.gyw.individual.backend.app;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.SpringVersion;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 *
 * @date 2023/2/23
 */
@SpringBootApplication(scanBasePackages = {"cn.gyw.individual.backend"})
@EnableJpaRepositories("cn.gyw.individual.backend.service")
@EntityScan("cn.gyw.individual.backend.service")
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                // 加载spring版本
                .main(SpringVersion.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
