package cn.gyw.backend.house.enums;

import org.apache.commons.lang3.StringUtils;

public enum HouseTypeEnum {

    NEW(1),
    OLD(2),
    OTHER(-1);

    private Integer code;

    HouseTypeEnum(Integer code) {
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
        for (HouseTypeEnum typeEnum : values()) {
            if (typeEnum.name().equalsIgnoreCase(enumName)) {
                return typeEnum.code;
            }
        }
        return OTHER.code;
    }
}
