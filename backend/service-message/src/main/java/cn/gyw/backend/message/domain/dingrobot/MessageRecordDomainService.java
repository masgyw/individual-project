package cn.gyw.backend.message.domain.dingrobot;

import cn.gyw.backend.message.domain.dingrobot.model.MessageSendModel;

public interface MessageRecordDomainService {

    /**
     * 发送消息
     *
     * @param messageSendModel
     */
    void sendMessage(MessageSendModel messageSendModel);

}
