// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.role.mapper;

import cn.gyw.backend.system.api.request.RoleCreateRequest;
import cn.gyw.backend.system.api.request.RoleQueryRequest;
import cn.gyw.backend.system.api.request.RoleUpdateRequest;
import cn.gyw.backend.system.api.response.RoleResponse;
import cn.gyw.backend.system.domain.permission.role.Role;
import cn.gyw.backend.system.domain.permission.role.creator.RoleCreator;
import cn.gyw.backend.system.domain.permission.role.query.RoleQuery;
import cn.gyw.backend.system.domain.permission.role.updater.RoleUpdater;
import cn.gyw.backend.system.domain.permission.role.vo.RoleVO;
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
public interface RoleMapper {
  RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

  Role dtoToEntity(RoleCreator dto);

  RoleUpdater request2Updater(RoleUpdateRequest request);

  RoleCreator request2Dto(RoleCreateRequest request);

  RoleQuery request2Query(RoleQueryRequest request);

  Role queryToEntity(RoleQuery query);

  RoleResponse vo2Response(RoleVO vo);

  default RoleResponse vo2CustomResponse(RoleVO vo) {
    RoleResponse response = vo2Response(vo);
    return response;
  }
}
