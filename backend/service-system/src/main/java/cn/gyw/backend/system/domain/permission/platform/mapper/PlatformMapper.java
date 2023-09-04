// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.platform.mapper;

import cn.gyw.backend.system.api.request.PlatformCreateRequest;
import cn.gyw.backend.system.api.request.PlatformQueryRequest;
import cn.gyw.backend.system.api.request.PlatformUpdateRequest;
import cn.gyw.backend.system.api.response.PlatformResponse;
import cn.gyw.backend.system.domain.permission.platform.Platform;
import cn.gyw.backend.system.domain.permission.platform.creator.PlatformCreator;
import cn.gyw.backend.system.domain.permission.platform.query.PlatformQuery;
import cn.gyw.backend.system.domain.permission.platform.updater.PlatformUpdater;
import cn.gyw.backend.system.domain.permission.platform.vo.PlatformVO;
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
public interface PlatformMapper {
  PlatformMapper INSTANCE = Mappers.getMapper(PlatformMapper.class);

  Platform dtoToEntity(PlatformCreator dto);

  PlatformUpdater request2Updater(PlatformUpdateRequest request);

  PlatformCreator request2Dto(PlatformCreateRequest request);

  PlatformQuery request2Query(PlatformQueryRequest request);

  Platform queryToEntity(PlatformQuery query);

  PlatformResponse vo2Response(PlatformVO vo);

  default PlatformResponse vo2CustomResponse(PlatformVO vo) {
    PlatformResponse response = vo2Response(vo);
    return response;
  }
}
