// ---Auto Generated ---
package cn.gyw.backend.order.domain.objectsku.mapper;

import cn.gyw.backend.order.api.request.ObjectSkuCreateRequest;
import cn.gyw.backend.order.api.request.ObjectSkuQueryRequest;
import cn.gyw.backend.order.api.request.ObjectSkuUpdateRequest;
import cn.gyw.backend.order.api.response.ObjectSkuResponse;
import cn.gyw.backend.order.converter.OrderMapper;
import cn.gyw.backend.order.domain.objectsku.ObjectSku;
import cn.gyw.backend.order.domain.objectsku.creator.ObjectSkuCreator;
import cn.gyw.backend.order.domain.objectsku.query.ObjectSkuQuery;
import cn.gyw.backend.order.domain.objectsku.updater.ObjectSkuUpdater;
import cn.gyw.backend.order.domain.objectsku.vo.ObjectSkuVO;
import cn.gyw.individual.commons.mapper.DateMapper;
import cn.gyw.individual.commons.mapper.GenericEnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                GenericEnumMapper.class,
                DateMapper.class,
                OrderMapper.class
        }
)
public interface ObjectSkuMapper {
    ObjectSkuMapper INSTANCE = Mappers.getMapper(ObjectSkuMapper.class);

    ObjectSku dtoToEntity(ObjectSkuCreator dto);

    ObjectSkuUpdater request2Updater(ObjectSkuUpdateRequest request);

    ObjectSkuCreator request2Dto(ObjectSkuCreateRequest request);

    ObjectSkuQuery request2Query(ObjectSkuQueryRequest request);

    ObjectSku queryToEntity(ObjectSkuQuery query);

    ObjectSkuResponse vo2Response(ObjectSkuVO vo);

    default ObjectSkuResponse vo2CustomResponse(ObjectSkuVO vo) {
        ObjectSkuResponse response = vo2Response(vo);
        return response;
    }
}
