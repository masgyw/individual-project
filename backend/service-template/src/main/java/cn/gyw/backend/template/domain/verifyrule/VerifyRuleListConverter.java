package cn.gyw.backend.template.domain.verifyrule;

import cn.gyw.backend.template.domain.verifyrule.rule.VerifyRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class VerifyRuleListConverter implements AttributeConverter<List<VerifyRule>, String> {

    final static Type type = new TypeReference<List<VerifyRule>>() {
    }.getType();

    @Override
    public String convertToDatabaseColumn(List<VerifyRule> feeRules) {
        return JSON.toJSONString(feeRules, SerializerFeature.WriteClassName);
    }

    @Override
    public List<VerifyRule> convertToEntityAttribute(String s) {
        if (StringUtils.isEmpty(s)) {
            return Collections.emptyList();
        } else {
            return JSON.parseObject(s, type);
        }
    }
}
