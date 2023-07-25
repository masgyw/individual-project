package cn.gyw.backend.message.domain.verifyrecord.service.checker;

import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import lombok.Data;

/**
 * @date 2023/7/24
 */
@Data
public class CheckContext {

    @FieldDesc(name = "账号")
    private String account;

    @FieldDesc(name = "发送间隔")
    private Integer sendInterval;

    @FieldDesc(name = "发送模板")
    private String templateCode;

    @FieldDesc(name = "发送最大次数")
    private Integer sendMaxTimes;
}
