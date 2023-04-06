// ---Auto Generated ---
package cn.gyw.backend.order.domain.objectsku.mapper;

import cn.gyw.backend.order.api.request.SkuAttributeCreateRequest;
import cn.gyw.backend.order.api.request.SkuAttributeQueryRequest;
import cn.gyw.backend.order.api.request.SkuAttributeUpdateRequest;
import cn.gyw.backend.order.api.response.SkuAttributeResponse;
import cn.gyw.backend.order.domain.objectsku.SkuAttribute;
import cn.gyw.backend.order.domain.objectsku.creator.SkuAttributeCreator;
import cn.gyw.backend.order.domain.objectsku.query.SkuAttributeQuery;
import cn.gyw.backend.order.domain.objectsku.updater.SkuAttributeUpdater;
import cn.gyw.backend.order.domain.objectsku.vo.SkuAttributeVO;
import cn.gyw.individual.commons.mapper.DateMapper;
import cn.gyw.individual.commons.mapper.GenericEnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
    uses = {
        GenericEnumMapper.class,
        DateMapper.class
    }
)
public interface SkuAttributeMapper {
  SkuAttributeMapper INSTANCE = Mappers.getMapper(SkuAttributeMapper.class);

  SkuAttribute dtoToEntity(SkuAttributeCreator dto);

  SkuAttributeUpdater request2Updater(SkuAttributeUpdateRequest request);

  SkuAttributeCreator request2Dto(SkuAttributeCreateRequest request);

  SkuAttributeQuery request2Query(SkuAttributeQueryRequest request);

  SkuAttribute queryToEntity(SkuAttributeQuery query);

  SkuAttributeResponse vo2Response(SkuAttributeVO vo);

  default SkuAttributeResponse vo2CustomResponse(SkuAttributeVO vo) {
    SkuAttributeResponse response = vo2Response(vo);
    return response;
  }
}
