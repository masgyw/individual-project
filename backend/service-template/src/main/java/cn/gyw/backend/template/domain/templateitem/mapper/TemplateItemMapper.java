// ---Auto Generated ---
package cn.gyw.backend.template.domain.templateitem.mapper;

import cn.gyw.backend.template.api.request.TemplateItemCreateRequest;
import cn.gyw.backend.template.api.request.TemplateItemQueryRequest;
import cn.gyw.backend.template.api.request.TemplateItemUpdateRequest;
import cn.gyw.backend.template.api.response.TemplateItemResponse;
import cn.gyw.backend.template.domain.templateitem.TemplateItem;
import cn.gyw.backend.template.domain.templateitem.creator.TemplateItemCreator;
import cn.gyw.backend.template.domain.templateitem.query.TemplateItemQuery;
import cn.gyw.backend.template.domain.templateitem.updater.TemplateItemUpdater;
import cn.gyw.backend.template.domain.templateitem.vo.TemplateItemVO;
import cn.gyw.backend.template.mapper.CustomMapper;
import cn.gyw.individual.commons.mapper.DateMapper;
import cn.gyw.individual.commons.mapper.GenericEnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                GenericEnumMapper.class,
                DateMapper.class,
                CustomMapper.class
        }
)
public interface TemplateItemMapper {
    TemplateItemMapper INSTANCE = Mappers.getMapper(TemplateItemMapper.class);

    TemplateItem dtoToEntity(TemplateItemCreator dto);

    TemplateItemUpdater request2Updater(TemplateItemUpdateRequest request);

    TemplateItemCreator request2Dto(TemplateItemCreateRequest request);

    TemplateItemQuery request2Query(TemplateItemQueryRequest request);

    TemplateItem queryToEntity(TemplateItemQuery query);

    TemplateItemResponse vo2Response(TemplateItemVO vo);

    default TemplateItemResponse vo2CustomResponse(TemplateItemVO vo) {
        TemplateItemResponse response = vo2Response(vo);
        return response;
    }
}
