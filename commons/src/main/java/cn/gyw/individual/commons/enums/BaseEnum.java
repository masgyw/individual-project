package cn.gyw.individual.commons.enums;

public interface BaseEnum<T extends Enum<T> & BaseEnum<T>> {

    /**
     * 获得code码存入数据库
     *
     * @return 获取编码
     */
    int getCode();

    /**
     * 获取编码名称，便于维护
     *
     * @return 编码名称
     */
    String getName();

    /**
     * 根据code码获取枚举
     *
     * @param cls
     * @param code
     * @param <T>
     * @return
     */
    static <T extends Enum<T> & BaseEnum<T>> T parseByCode(Class<T> cls, Integer code) {
        for (T t : cls.getEnumConstants()) {
            if (t.getCode() == code) {
                return t;
            }
        }
        return null;
    }
}
