package cn.gyw.individual.commons.converter;

import cn.gyw.individual.commons.model.DictValue;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DictValueListConverter implements AttributeConverter<List<DictValue>, String> {

    final static Type type = new TypeReference<List<DictValue>>() {
    }.getType();

    @Override
    public String convertToDatabaseColumn(List<DictValue> feeRules) {
        return JSON.toJSONString(feeRules);
    }

    @Override
    public List<DictValue> convertToEntityAttribute(String s) {
        if (StringUtils.isEmpty(s)) {
            return Collections.emptyList();
        } else {
            return JSON.parseObject(s, type);
        }
    }
}
