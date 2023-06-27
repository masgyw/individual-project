// ---Auto Generated ---
package cn.gyw.backend.message.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;

@Schema
public class MessageRecordUpdateRequest implements Request {
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

  private Long id;

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

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
