// ---Auto Generated ---
package cn.gyw.backend.asset.domain.warehouse.mapper;

import cn.gyw.backend.asset.api.request.WarehouseCreateRequest;
import cn.gyw.backend.asset.api.request.WarehouseQueryRequest;
import cn.gyw.backend.asset.api.request.WarehouseUpdateRequest;
import cn.gyw.backend.asset.api.response.WarehouseResponse;
import cn.gyw.backend.asset.domain.warehouse.Warehouse;
import cn.gyw.backend.asset.domain.warehouse.creator.WarehouseCreator;
import cn.gyw.backend.asset.domain.warehouse.query.WarehouseQuery;
import cn.gyw.backend.asset.domain.warehouse.updater.WarehouseUpdater;
import cn.gyw.backend.asset.domain.warehouse.vo.WarehouseVO;
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
public interface WarehouseMapper {
  WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

  Warehouse dtoToEntity(WarehouseCreator dto);

  WarehouseUpdater request2Updater(WarehouseUpdateRequest request);

  WarehouseCreator request2Dto(WarehouseCreateRequest request);

  WarehouseQuery request2Query(WarehouseQueryRequest request);

  Warehouse queryToEntity(WarehouseQuery query);

  WarehouseResponse vo2Response(WarehouseVO vo);

  default WarehouseResponse vo2CustomResponse(WarehouseVO vo) {
    WarehouseResponse response = vo2Response(vo);
    return response;
  }
}
