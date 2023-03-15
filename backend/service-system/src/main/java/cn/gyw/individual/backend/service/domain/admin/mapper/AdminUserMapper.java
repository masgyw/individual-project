// ---Auto Generated ---
package cn.gyw.individual.backend.service.domain.admin.mapper;

import cn.gyw.individual.backend.service.api.request.AdminUserCreateRequest;
import cn.gyw.individual.backend.service.api.request.AdminUserQueryRequest;
import cn.gyw.individual.backend.service.api.request.AdminUserUpdateRequest;
import cn.gyw.individual.backend.service.api.response.AdminUserResponse;
import cn.gyw.individual.backend.service.domain.admin.AdminUser;
import cn.gyw.individual.backend.service.domain.admin.creator.AdminUserCreator;
import cn.gyw.individual.backend.service.domain.admin.query.AdminUserQuery;
import cn.gyw.individual.backend.service.domain.admin.updater.AdminUserUpdater;
import cn.gyw.individual.backend.service.domain.admin.vo.AdminUserVO;
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
public interface AdminUserMapper {
  AdminUserMapper INSTANCE = Mappers.getMapper(AdminUserMapper.class);

  AdminUser dtoToEntity(AdminUserCreator dto);

  AdminUserUpdater request2Updater(AdminUserUpdateRequest request);

  AdminUserCreator request2Dto(AdminUserCreateRequest request);

  AdminUserQuery request2Query(AdminUserQueryRequest request);

  AdminUser queryToEntity(AdminUserQuery query);

  AdminUserResponse vo2Response(AdminUserVO vo);

  default AdminUserResponse vo2CustomResponse(AdminUserVO vo) {
    AdminUserResponse response = vo2Response(vo);
    return response;
  }
}
