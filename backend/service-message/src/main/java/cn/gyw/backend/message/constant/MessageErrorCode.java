package cn.gyw.backend.message.constant;

import cn.gyw.individual.commons.enums.BaseEnum;

/**
 * @date 2023/7/10
 */
public enum MessageErrorCode implements BaseEnum<MessageErrorCode> {

    TEMPLATE_NOT_FIND(1601001, "模板不存在"),

    MESSAGE_SEND_FAST(1601002, "消息发送过于频繁"),
    ;

    private Integer code;
    private String name;

    MessageErrorCode(Integer code, String name) {
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
}
