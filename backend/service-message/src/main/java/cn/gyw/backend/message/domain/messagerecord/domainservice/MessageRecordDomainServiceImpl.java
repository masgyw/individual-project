package cn.gyw.backend.message.domain.messagerecord.domainservice;

import cn.gyw.backend.message.constant.MessageErrorCode;
import cn.gyw.backend.message.domain.messagerecord.domainservice.model.MessageSendModel;
import cn.gyw.backend.message.domain.messagerecord.mapper.MessageRecordMapper;
import cn.gyw.backend.message.domain.messagerecord.repository.MessageRecordRepository;
import cn.gyw.backend.message.domain.messagetemplate.MessageTemplate;
import cn.gyw.backend.message.domain.messagetemplate.repository.MessageTemplateRepository;
import cn.gyw.individual.commons.enums.BaseEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import jodd.util.StringTemplateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<MessageTemplate> template = messageTemplateRepository.findByTemplateCode(messageSendModel.getTemplateCode());
        if (!template.isPresent()) {
            throw new BusinessException(MessageErrorCode.TEMPLATE_NOT_FIND);
        }
        //模板内容替换
        String content = StringTemplateParser
                .ofMap(messageSendModel.getParams())
                .apply(template.get().getTemplate());
        EntityOperations
                .doCreate(messageRecordRepository)
                .create(() -> MessageRecordMapper.INSTANCE.model2Entity(messageSendModel))
                .update(e -> e.init(content))
                .execute();
    }
}
