package cn.gyw.backend.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MessageServiceTest {

  @Autowired
  private IMessageRecordDomainService messageRecordDomainService;

  @Autowired
  private IMessageTemplateService messageTemplateService;

  @Test
  public void testAddTemplate(){
    MessageTemplateCreator templateCreator = new MessageTemplateCreator();
    templateCreator.setTemplate("发送短信，验证码为:${verifyCode}");
    templateCreator.setTemplateCode("sms_register");
    templateCreator.setName("短信注册");
    templateCreator.setTemplateType(TemplateType.SMS);
    templateCreator.setDescription("短信注册模板");
    messageTemplateService.createMessageTemplate(templateCreator);
  }


  @Test
  public void testSmsSend(){
    MessageSendModel messageSendModel = new MessageSendModel();
    messageSendModel.setNoticeType(NoticeType.SMS);
    messageSendModel.setMsgType(MsgTypeEnum.VERIFY);
    messageSendModel.setTemplateCode("sms_register");
    Map<String,Object> params = Maps.newHashMap();
    params.put(MessageConstants.ACCOUNT,"13888888888");
    messageSendModel.setParams(params);
    messageRecordDomainService.sendMessage(messageSendModel);
  }

}