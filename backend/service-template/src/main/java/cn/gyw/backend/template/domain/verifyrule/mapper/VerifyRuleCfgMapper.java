// ---Auto Generated ---
package cn.gyw.backend.template.domain.verifyrule.mapper;

import cn.gyw.backend.template.api.request.VerifyRuleCfgCreateRequest;
import cn.gyw.backend.template.api.request.VerifyRuleCfgQueryRequest;
import cn.gyw.backend.template.api.request.VerifyRuleCfgUpdateRequest;
import cn.gyw.backend.template.api.response.VerifyRuleCfgResponse;
import cn.gyw.backend.template.domain.verifyrule.VerifyRuleCfg;
import cn.gyw.backend.template.domain.verifyrule.creator.VerifyRuleCfgCreator;
import cn.gyw.backend.template.domain.verifyrule.query.VerifyRuleCfgQuery;
import cn.gyw.backend.template.domain.verifyrule.updater.VerifyRuleCfgUpdater;
import cn.gyw.backend.template.domain.verifyrule.vo.VerifyRuleCfgVO;
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
public interface VerifyRuleCfgMapper {
  VerifyRuleCfgMapper INSTANCE = Mappers.getMapper(VerifyRuleCfgMapper.class);

  VerifyRuleCfg dtoToEntity(VerifyRuleCfgCreator dto);

  VerifyRuleCfgUpdater request2Updater(VerifyRuleCfgUpdateRequest request);

  VerifyRuleCfgCreator request2Dto(VerifyRuleCfgCreateRequest request);

  VerifyRuleCfgQuery request2Query(VerifyRuleCfgQueryRequest request);

  VerifyRuleCfg queryToEntity(VerifyRuleCfgQuery query);

  VerifyRuleCfgResponse vo2Response(VerifyRuleCfgVO vo);

  default VerifyRuleCfgResponse vo2CustomResponse(VerifyRuleCfgVO vo) {
    VerifyRuleCfgResponse response = vo2Response(vo);
    return response;
  }
}
