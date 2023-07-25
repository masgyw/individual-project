package cn.gyw.backend.message.config;

import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @date 2023/7/24
 */
@Data
@Component
@ConfigurationProperties(prefix = "message")
public class MessageProperties {

    @FieldDesc(name = "验证码长度")
    private Integer verifyLength;

    @FieldDesc(name = "有效的时长(分钟)")
    private Integer validTime;

    @FieldDesc(name = "发送间隔(分钟)")
    private Integer sendInterval;

    @FieldDesc(name = "某类短信当天发送最多次数(根据模板判断)")
    private Integer sendMaxTimes;
}
