package cn.gyw.backend;

import cn.gyw.individual.commons.utils.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动后处理
 *
 * @date 2023/2/23
 */
@Slf4j
@Component
public class AppRunner implements ApplicationRunner {

    @Value("${server.port:8080}")
    private Integer port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String localIp = SystemUtil.getLocalIp();
        log.info("home page = http://{}:{}", localIp, port);
        log.info("swagger ui = http://{}:{}/swagger-ui.html", localIp, port);
        log.info("knife4j ui = http://{}:{}/doc.html", localIp, port);
    }
}
