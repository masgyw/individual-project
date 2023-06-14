// ---Auto Generated ---
package cn.gyw.backend.message.api.request;

import cn.gyw.backend.message.domain.smssendrecord.SmsType;
import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.String;

@Schema
public class SmsSendRecordCreateRequest implements Request {
    @Schema(
            title = "phone"
    )
    private String phone;

    @Schema(
            title = "verifyCode"
    )
    private String verifyCode;

    @Schema(
            title = "smsType"
    )
    private SmsType smsType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public SmsType getSmsType() {
        return smsType;
    }

    public void setSmsType(SmsType smsType) {
        this.smsType = smsType;
    }
}
