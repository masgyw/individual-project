package cn.gyw.backend.house.domain.house.service.fileresolver;

import cn.gyw.backend.house.domain.house.creator.HouseCreator;
import cn.gyw.backend.house.enums.HouseTypeEnum;
import cn.gyw.backend.house.enums.OriginTypeEnum;
import cn.gyw.individual.commons.utils.DateUtil;
import cn.gyw.individual.commons.utils.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @date 2023/9/28
 */
@Slf4j
public abstract class AbstractDataFileResolver implements DataFileResolver {
    /**
     * 数据对象 Field 缓存
     */
    private final Map<String, Field> creatorFieldMap = new ConcurrentHashMap<>();

    protected HouseCreator buildHouseCreator(String[] enHeaders, String[] data, String source) {
        HouseCreator houseCreator = new HouseCreator();
        // 文件名
        houseCreator.setSourceFile(source);
        // 文件数据
        for (int i = 0, len = enHeaders.length; i < len; i++) {
            String prop = enHeaders[i];
            try {
                Field f = getField(prop);
                if (f.getName().equals("crawlDate")) {
                    f.set(houseCreator, DateUtil.parse(data[i], DateUtil.YYYYMMDD));
                    continue;
                }
                if (f.getType() == BigDecimal.class) {
                    BigDecimal num = BigDecimal.ZERO;
                    if (StringUtils.isNotBlank(data[i])) {
                        num = RegexUtil.getBeginNum(data[i]);
                    }
                    f.set(houseCreator, num);
                    continue;
                }
                if (f.getName().equals("houseType")) {
                    f.set(houseCreator, HouseTypeEnum.getCode(data[i]));
                    continue;
                }
                if (f.getName().equals("originType")) {
                    f.set(houseCreator, OriginTypeEnum.getCode(data[i]));
                    continue;
                }
                // 其他字段，默认处理
                f.set(houseCreator, data[i]);
            } catch (Exception e) {
                log.error("write filed error [" + prop + "] , error :", e);
            }
        }
        return houseCreator;
    }

    private Field getField(String prop) {
        return creatorFieldMap.computeIfAbsent(prop, (k) -> {
            Field field = ReflectionUtils.findField(HouseCreator.class, prop);
            Objects.requireNonNull(field).setAccessible(true);
            return field;
        });
    }
}
