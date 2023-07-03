// ---Auto Generated ---
package cn.gyw.backend.message.domain.messagerecord.mapper;

import cn.gyw.backend.message.api.request.MessageRecordCreateRequest;
import cn.gyw.backend.message.api.request.MessageRecordQueryRequest;
import cn.gyw.backend.message.api.request.MessageRecordUpdateRequest;
import cn.gyw.backend.message.api.response.MessageRecordResponse;
import cn.gyw.backend.message.domain.messagerecord.MessageMapper;
import cn.gyw.backend.message.domain.messagerecord.MessageRecord;
import cn.gyw.backend.message.domain.messagerecord.creator.MessageRecordCreator;
import cn.gyw.backend.message.domain.messagerecord.query.MessageRecordQuery;
import cn.gyw.backend.message.domain.messagerecord.updater.MessageRecordUpdater;
import cn.gyw.backend.message.domain.messagerecord.vo.MessageRecordVO;
import cn.gyw.individual.commons.mapper.DateMapper;
import cn.gyw.individual.commons.mapper.GenericEnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                GenericEnumMapper.class,
                DateMapper.class,
                MessageMapper.class
        }
)
public interface MessageRecordMapper {
    MessageRecordMapper INSTANCE = Mappers.getMapper(MessageRecordMapper.class);

    MessageRecord dtoToEntity(MessageRecordCreator dto);

    MessageRecordUpdater request2Updater(MessageRecordUpdateRequest request);

    MessageRecordCreator request2Dto(MessageRecordCreateRequest request);

    MessageRecordQuery request2Query(MessageRecordQueryRequest request);

    MessageRecord queryToEntity(MessageRecordQuery query);

    MessageRecordResponse vo2Response(MessageRecordVO vo);

    default MessageRecordResponse vo2CustomResponse(MessageRecordVO vo) {
        MessageRecordResponse response = vo2Response(vo);
        return response;
    }
}
