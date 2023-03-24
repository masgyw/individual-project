package cn.gyw.backend.house.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据来源
 */
public enum OriginTypeEnum {

    FTX(1),
    TC58(2),
    OTHER(-1);

    private Integer code;

    OriginTypeEnum(Integer code) {
        this.code = code;
    }

    /**
     * @param enumName 枚举名称
     * @return 枚举值
     */
    public static Integer getCode(String enumName) {
        if (StringUtils.isEmpty(enumName)) {
            return OTHER.code;
        }
        for (OriginTypeEnum typeEnum : values()) {
            if (typeEnum.name().equalsIgnoreCase(enumName)) {
                return typeEnum.code;
            }
        }
        return OTHER.code;
    }
}
