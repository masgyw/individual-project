package cn.gyw.backend.asset.domain.assetrecord;


import cn.gyw.individual.commons.enums.BaseEnum;

import java.util.Optional;

public enum InOutType implements BaseEnum<InOutType> {

    IN(1, "入库"),
    OUT(2, "出库");

    private Integer code;
    private String name;

    InOutType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<InOutType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(InOutType.class, code));
    }
}