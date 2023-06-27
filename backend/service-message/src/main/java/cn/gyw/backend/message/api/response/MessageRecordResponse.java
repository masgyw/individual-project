// ---Auto Generated ---
package cn.gyw.backend.message.api.response;

import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.String;

@Schema
public class MessageRecordResponse extends AbstractResponse {
  @Schema(
      title = "模板编码"
  )
  private String templateCode;

  @Schema(
      title = "content"
  )
  private String content;

  @Schema(
      title = "发送参数"
  )
  private String params;

  @Schema(
      title = "消息类型"
  )
  private Integer msgType;

  @Schema(
      title = "noticeType"
  )
  private Integer noticeType;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  public String getTemplateCode() {
    return templateCode;
  }

  public void setTemplateCode(String templateCode) {
    this.templateCode = templateCode;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getParams() {
    return params;
  }

  public void setParams(String params) {
    this.params = params;
  }

  public Integer getMsgType() {
    return msgType;
  }

  public void setMsgType(Integer msgType) {
    this.msgType = msgType;
  }

  public Integer getNoticeType() {
    return noticeType;
  }

  public void setNoticeType(Integer noticeType) {
    this.noticeType = noticeType;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
