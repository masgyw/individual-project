package cn.gyw.backend.message.third;

import cn.gyw.individual.starters.extension.executor.Extension;

/**
 * @date 2023/7/24
 */
@Extension(bizId = SmsBizId.ALI_SMS_BIZ)
public class AliSmsSendServiceImpl implements SmsSendService {
    @Override
    public boolean sendSms(SmsSendModel sendModel) {
        //发送逻辑
        System.out.println("使用阿里平台发送短信");
        return true;
    }
}
