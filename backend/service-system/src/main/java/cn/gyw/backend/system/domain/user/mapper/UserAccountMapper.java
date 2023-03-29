// ---Auto Generated ---
package cn.gyw.backend.system.domain.user.mapper;

import cn.gyw.backend.system.api.request.UserAccountCreateRequest;
import cn.gyw.backend.system.api.request.UserAccountQueryRequest;
import cn.gyw.backend.system.api.request.UserAccountUpdateRequest;
import cn.gyw.backend.system.api.response.UserAccountResponse;
import cn.gyw.backend.system.domain.user.UserAccount;
import cn.gyw.backend.system.domain.user.creator.UserAccountCreator;
import cn.gyw.backend.system.domain.user.query.UserAccountQuery;
import cn.gyw.backend.system.domain.user.updater.UserAccountUpdater;
import cn.gyw.backend.system.domain.user.vo.UserAccountVO;
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
public interface UserAccountMapper {
  UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);

  UserAccount dtoToEntity(UserAccountCreator dto);

  UserAccountUpdater request2Updater(UserAccountUpdateRequest request);

  UserAccountCreator request2Dto(UserAccountCreateRequest request);

  UserAccountQuery request2Query(UserAccountQueryRequest request);

  UserAccount queryToEntity(UserAccountQuery query);

  UserAccountResponse vo2Response(UserAccountVO vo);

  default UserAccountResponse vo2CustomResponse(UserAccountVO vo) {
    UserAccountResponse response = vo2Response(vo);
    return response;
  }
}
