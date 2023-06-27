package cn.gyw.backend.message.domain.messagerecord.domainservice;

import cn.gyw.backend.message.domain.messagerecord.domainservice.model.MessageSendModel;

public interface MessageRecordDomainService {

    /**
     * 发送消息
     *
     * @param messageSendModel
     */
    void sendMessage(MessageSendModel messageSendModel);

}
