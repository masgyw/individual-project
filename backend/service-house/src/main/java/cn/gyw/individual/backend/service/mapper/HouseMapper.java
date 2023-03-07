// ---Auto Generated ---
package cn.gyw.individual.backend.service.mapper;

import cn.gyw.individual.backend.service.creator.HouseCreator;
import cn.gyw.individual.backend.service.domain.House;
import cn.gyw.individual.backend.service.query.HouseQuery;
import cn.gyw.individual.backend.service.request.HouseCreateRequest;
import cn.gyw.individual.backend.service.request.HouseQueryRequest;
import cn.gyw.individual.backend.service.request.HouseUpdateRequest;
import cn.gyw.individual.backend.service.response.HouseResponse;
import cn.gyw.individual.backend.service.updater.HouseUpdater;
import cn.gyw.individual.backend.service.vo.HouseVO;
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
public interface HouseMapper {
  HouseMapper INSTANCE = Mappers.getMapper(HouseMapper.class);

  House dtoToEntity(HouseCreator dto);

  HouseUpdater request2Updater(HouseUpdateRequest request);

  HouseCreator request2Dto(HouseCreateRequest request);

  HouseQuery request2Query(HouseQueryRequest request);

  House queryToEntity(HouseQuery query);

  HouseResponse vo2Response(HouseVO vo);

  default HouseResponse vo2CustomResponse(HouseVO vo) {
    HouseResponse response = vo2Response(vo);
    return response;
  }
}
