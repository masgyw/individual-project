package cn.gyw.backend.message.domain.messagerecord.events;

import cn.gyw.backend.message.domain.messagerecord.MessageRecord;
import lombok.Value;

public interface MessageRecordEvents {

  @Value
  class MessageRecordCreateEvent {
    MessageRecord messageRecord;
  }

}