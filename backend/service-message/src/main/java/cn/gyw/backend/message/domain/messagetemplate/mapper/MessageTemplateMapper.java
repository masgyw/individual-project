// ---Auto Generated ---
package cn.gyw.backend.message.domain.messagetemplate.mapper;

import cn.gyw.backend.message.api.request.MessageTemplateCreateRequest;
import cn.gyw.backend.message.api.request.MessageTemplateQueryRequest;
import cn.gyw.backend.message.api.request.MessageTemplateUpdateRequest;
import cn.gyw.backend.message.api.response.MessageTemplateResponse;
import cn.gyw.backend.message.domain.messagetemplate.MessageTemplate;
import cn.gyw.backend.message.domain.messagetemplate.creator.MessageTemplateCreator;
import cn.gyw.backend.message.domain.messagetemplate.query.MessageTemplateQuery;
import cn.gyw.backend.message.domain.messagetemplate.updater.MessageTemplateUpdater;
import cn.gyw.backend.message.domain.messagetemplate.vo.MessageTemplateVO;
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
public interface MessageTemplateMapper {
  MessageTemplateMapper INSTANCE = Mappers.getMapper(MessageTemplateMapper.class);

  MessageTemplate dtoToEntity(MessageTemplateCreator dto);

  MessageTemplateUpdater request2Updater(MessageTemplateUpdateRequest request);

  MessageTemplateCreator request2Dto(MessageTemplateCreateRequest request);

  MessageTemplateQuery request2Query(MessageTemplateQueryRequest request);

  MessageTemplate queryToEntity(MessageTemplateQuery query);

  MessageTemplateResponse vo2Response(MessageTemplateVO vo);

  default MessageTemplateResponse vo2CustomResponse(MessageTemplateVO vo) {
    MessageTemplateResponse response = vo2Response(vo);
    return response;
  }
}
