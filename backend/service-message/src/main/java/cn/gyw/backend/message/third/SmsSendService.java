package cn.gyw.backend.message.third;

/**
 * @date 2023/7/24
 */
public interface SmsSendService {

    boolean sendSms(SmsSendModel sendModel);

}
