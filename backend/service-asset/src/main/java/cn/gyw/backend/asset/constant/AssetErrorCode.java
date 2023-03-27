package cn.gyw.backend.asset.constant;

import cn.gyw.individual.commons.enums.BaseEnum;

import java.util.Optional;

/**
 * @date 2023/3/27
 */
public enum AssetErrorCode implements BaseEnum<AssetErrorCode> {
    ASSET_HAS_IN(10010026, "资产已经入库"),
    ASSET_HAS_OUT(10010027, "资产已经出库"),
    ASSET_CODE_NOT_EXIST(10010028, "资产编码不存在");

    private Integer code;

    private String name;

    AssetErrorCode(Integer code, String name) {
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

    public static Optional<AssetErrorCode> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(AssetErrorCode.class, code));
    }
}
