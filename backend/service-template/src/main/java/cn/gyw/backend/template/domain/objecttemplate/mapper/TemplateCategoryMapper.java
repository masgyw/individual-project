// ---Auto Generated ---
package cn.gyw.backend.template.domain.objecttemplate.mapper;

import cn.gyw.backend.template.api.request.TemplateCategoryCreateRequest;
import cn.gyw.backend.template.api.request.TemplateCategoryQueryRequest;
import cn.gyw.backend.template.api.request.TemplateCategoryUpdateRequest;
import cn.gyw.backend.template.api.response.TemplateCategoryResponse;
import cn.gyw.backend.template.domain.objecttemplate.TemplateCategory;
import cn.gyw.backend.template.domain.objecttemplate.creator.TemplateCategoryCreator;
import cn.gyw.backend.template.domain.objecttemplate.query.TemplateCategoryQuery;
import cn.gyw.backend.template.domain.objecttemplate.updater.TemplateCategoryUpdater;
import cn.gyw.backend.template.domain.objecttemplate.vo.TemplateCategoryVO;
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
public interface TemplateCategoryMapper {
  TemplateCategoryMapper INSTANCE = Mappers.getMapper(TemplateCategoryMapper.class);

  TemplateCategory dtoToEntity(TemplateCategoryCreator dto);

  TemplateCategoryUpdater request2Updater(TemplateCategoryUpdateRequest request);

  TemplateCategoryCreator request2Dto(TemplateCategoryCreateRequest request);

  TemplateCategoryQuery request2Query(TemplateCategoryQueryRequest request);

  TemplateCategory queryToEntity(TemplateCategoryQuery query);

  TemplateCategoryResponse vo2Response(TemplateCategoryVO vo);

  default TemplateCategoryResponse vo2CustomResponse(TemplateCategoryVO vo) {
    TemplateCategoryResponse response = vo2Response(vo);
    return response;
  }
}
