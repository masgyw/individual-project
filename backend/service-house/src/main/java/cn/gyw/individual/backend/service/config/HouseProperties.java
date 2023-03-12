package cn.gyw.individual.backend.service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @date 2022/1/17 19:23
 */
@Component
@Getter
public class HouseProperties {

    // CSV 数据文件存储目录
    @Value("${house.dir.storage}")
    private String storageDir;
}
