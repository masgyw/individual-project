package cn.gyw.backend.message.domain.messagerecord;

import cn.gyw.backend.message.domain.messagetemplate.TemplateType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @date 2023/7/3
 */
public class MessageMapper {

    public NoticeType int2NoticeType(Integer code) {
        return NoticeType.of(code).orElse(null);
    }

    public Integer noticeType2Int(NoticeType noticeType) {
        return noticeType.getCode();
    }

    public MsgTypeEnum int2MsgTypeEnum(Integer code) {
        return MsgTypeEnum.of(code).orElse(null);
    }

    public Integer msgTypeEnum2Int(MsgTypeEnum msgTypeEnum) {
        return msgTypeEnum.getCode();
    }

    public TemplateType int2TemplateType(Integer code) {
        return TemplateType.of(code).orElse(null);
    }

    public Integer templateType2Int(TemplateType templateType) {
        return templateType.getCode();
    }

    public Map<String, Object> string2Map(String str) {
        JSONObject jsonObject = JSONObject.parseObject(str);
        return jsonObject;
    }

    public String map2String(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }

}
