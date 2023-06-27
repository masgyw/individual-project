package cn.gyw.backend.message.domain.messagerecord.domainservice;

import cn.gyw.backend.message.domain.messagerecord.domainservice.model.MessageSendModel;
import cn.gyw.backend.message.domain.messagerecord.repository.MessageRecordRepository;
import cn.gyw.backend.message.domain.messagetemplate.repository.MessageTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date 2023/6/27
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class MessageRecordDomainServiceImpl implements MessageRecordDomainService {

    private final MessageTemplateRepository messageTemplateRepository;

    private final MessageRecordRepository messageRecordRepository;

    @Override
    public void sendMessage(MessageSendModel messageSendModel) {
    }
}
