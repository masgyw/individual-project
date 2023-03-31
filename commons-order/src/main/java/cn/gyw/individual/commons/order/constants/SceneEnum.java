package cn.gyw.individual.commons.order.constants;

/**
 * @date 2023/3/31
 */
public enum SceneEnum {

    BIZ_ORDER(1, "订单场景");

    private final Integer code;

    private final String name;

    SceneEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
