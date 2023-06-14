// ---Auto Generated ---
package cn.gyw.backend.message.api.response;

import cn.gyw.backend.message.domain.smssendrecord.SmsType;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.String;

@Schema
public class SmsSendRecordResponse extends AbstractResponse {
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

    @Schema(
            title = "validStatus"
    )
    private ValidStatus validStatus;

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

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }
}
