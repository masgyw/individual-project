package cn.gyw.backend.order.domain.objectsku;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Streams;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.stream.Collectors;

public class ListLongConverter implements AttributeConverter<List<Long>, String> {

    @Override
    public String convertToDatabaseColumn(List<Long> longs) {
        return Joiner.on(",").join(longs);
    }

    @Override
    public List<Long> convertToEntityAttribute(String str) {
        return Streams.stream(Splitter.on(",").split(str))
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
}
