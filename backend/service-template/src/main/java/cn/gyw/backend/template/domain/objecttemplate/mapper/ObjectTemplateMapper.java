// ---Auto Generated ---
package cn.gyw.backend.template.domain.objecttemplate.mapper;

import cn.gyw.backend.template.api.request.ObjectTemplateCreateRequest;
import cn.gyw.backend.template.api.request.ObjectTemplateQueryRequest;
import cn.gyw.backend.template.api.request.ObjectTemplateUpdateRequest;
import cn.gyw.backend.template.api.response.ObjectTemplateResponse;
import cn.gyw.backend.template.domain.objecttemplate.ObjectTemplate;
import cn.gyw.backend.template.domain.objecttemplate.creator.ObjectTemplateCreator;
import cn.gyw.backend.template.domain.objecttemplate.query.ObjectTemplateQuery;
import cn.gyw.backend.template.domain.objecttemplate.updater.ObjectTemplateUpdater;
import cn.gyw.backend.template.domain.objecttemplate.vo.ObjectTemplateVO;
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
public interface ObjectTemplateMapper {
  ObjectTemplateMapper INSTANCE = Mappers.getMapper(ObjectTemplateMapper.class);

  ObjectTemplate dtoToEntity(ObjectTemplateCreator dto);

  ObjectTemplateUpdater request2Updater(ObjectTemplateUpdateRequest request);

  ObjectTemplateCreator request2Dto(ObjectTemplateCreateRequest request);

  ObjectTemplateQuery request2Query(ObjectTemplateQueryRequest request);

  ObjectTemplate queryToEntity(ObjectTemplateQuery query);

  ObjectTemplateResponse vo2Response(ObjectTemplateVO vo);

  default ObjectTemplateResponse vo2CustomResponse(ObjectTemplateVO vo) {
    ObjectTemplateResponse response = vo2Response(vo);
    return response;
  }
}
