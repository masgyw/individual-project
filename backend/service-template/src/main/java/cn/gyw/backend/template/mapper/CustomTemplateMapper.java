package cn.gyw.backend.template.mapper;

import cn.gyw.backend.template.domain.selectdict.DictType;
import cn.gyw.backend.template.domain.templateitem.InputType;

/**
 * 枚举自定义转化
 * 在实体类中的枚举都要进行配置
 */
public class CustomTemplateMapper {

    public Integer type2Int(InputType type) {
        return type.getCode();
    }

    public InputType int2Type(Integer code) {
        return InputType.of(code).orElse(InputType.TEXT);
    }

    /**
     * 字典码值枚举映射
     *
     * @param dictType 字典类型
     * @return 码值
     */
    public Integer dictType2Int(DictType dictType) {
        return dictType.getCode();
    }

    public DictType int2DictType(Integer code) {
        return DictType.of(code).orElse(DictType.SELECT_LIST);
    }

}
