package cn.gyw.backend.message.domain.messagerecord.domainservice.model;

import java.util.Map;

import cn.gyw.backend.message.domain.messagerecord.MsgTypeEnum;
import cn.gyw.backend.message.domain.messagerecord.NoticeType;
import cn.gyw.individual.plugin.codegen.annotations.FieldDesc;
import lombok.Data;

@Data
public class MessageSendModel {

    @FieldDesc(name = "通知类型")
    private NoticeType noticeType;

    @FieldDesc(name = "模板编码")
    private String templateCode;

    @FieldDesc(name = "发送参数")
    private Map<String, Object> params;

    @FieldDesc(name = "消息类型")
    private MsgTypeEnum msgType;

}