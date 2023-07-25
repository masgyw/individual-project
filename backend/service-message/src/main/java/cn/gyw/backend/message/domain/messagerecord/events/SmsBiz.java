package cn.gyw.backend.message.domain.messagerecord.events;

import cn.gyw.individual.commons.enums.BaseEnum;
import cn.gyw.individual.starters.extension.executor.BizScene;

import java.util.Optional;

/**
 * @date 2023/7/24
 */
public enum SmsBiz implements BaseEnum<SmsBiz>, BizScene {
    ALI(1, "ali");

    private Integer code;
    private String name;

    SmsBiz(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBizId() {
        return this.name;
    }

    public static Optional<SmsBiz> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(SmsBiz.class, code));
    }
}
