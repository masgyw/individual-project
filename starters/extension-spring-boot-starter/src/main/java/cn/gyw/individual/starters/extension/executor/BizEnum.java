package cn.gyw.individual.starters.extension.executor;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @date 2023/7/21
 */
public enum BizEnum implements BizScene {
    DEFAULT("default", "默认业务");

    private String bizId;
    @Getter
    private String desc;

    BizEnum(String bizId, String desc) {
        this.bizId = bizId;
        this.desc = desc;
    }

    @Override
    public String getBizId() {
        return bizId;
    }

    public static Optional<BizEnum> of(String bizId) {
        return Arrays.stream(BizEnum.values())
                .filter(biz -> Objects.equals(biz.getBizId(), bizId)).findFirst();
    }
}
