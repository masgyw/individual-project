// ---Auto Generated ---
package cn.gyw.backend.system.domain.user.mapper;

import cn.gyw.backend.system.api.request.UserBaseCreateRequest;
import cn.gyw.backend.system.api.request.UserBaseQueryRequest;
import cn.gyw.backend.system.api.request.UserBaseUpdateRequest;
import cn.gyw.backend.system.api.response.UserBaseResponse;
import cn.gyw.backend.system.domain.user.UserBase;
import cn.gyw.backend.system.domain.user.creator.UserBaseCreator;
import cn.gyw.backend.system.domain.user.query.UserBaseQuery;
import cn.gyw.backend.system.domain.user.updater.UserBaseUpdater;
import cn.gyw.backend.system.domain.user.vo.UserBaseVO;
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
public interface UserBaseMapper {
  UserBaseMapper INSTANCE = Mappers.getMapper(UserBaseMapper.class);

  UserBase dtoToEntity(UserBaseCreator dto);

  UserBaseUpdater request2Updater(UserBaseUpdateRequest request);

  UserBaseCreator request2Dto(UserBaseCreateRequest request);

  UserBaseQuery request2Query(UserBaseQueryRequest request);

  UserBase queryToEntity(UserBaseQuery query);

  UserBaseResponse vo2Response(UserBaseVO vo);

  default UserBaseResponse vo2CustomResponse(UserBaseVO vo) {
    UserBaseResponse response = vo2Response(vo);
    return response;
  }
}
