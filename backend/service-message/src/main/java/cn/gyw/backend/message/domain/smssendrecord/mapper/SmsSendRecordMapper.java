// ---Auto Generated ---
package cn.gyw.backend.message.domain.smssendrecord.mapper;

import cn.gyw.backend.message.domain.smssendrecord.SmsSendRecord;
import cn.gyw.backend.message.api.request.SmsSendRecordCreateRequest;
import cn.gyw.backend.message.api.request.SmsSendRecordQueryRequest;
import cn.gyw.backend.message.api.request.SmsSendRecordUpdateRequest;
import cn.gyw.backend.message.api.response.SmsSendRecordResponse;
import cn.gyw.backend.message.domain.smssendrecord.creator.SmsSendRecordCreator;
import cn.gyw.backend.message.domain.smssendrecord.query.SmsSendRecordQuery;
import cn.gyw.backend.message.domain.smssendrecord.updater.SmsSendRecordUpdater;
import cn.gyw.backend.message.domain.smssendrecord.vo.SmsSendRecordVO;
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
public interface SmsSendRecordMapper {
    SmsSendRecordMapper INSTANCE = Mappers.getMapper(SmsSendRecordMapper.class);

    SmsSendRecord dtoToEntity(SmsSendRecordCreator dto);

    SmsSendRecordUpdater request2Updater(SmsSendRecordUpdateRequest request);

    SmsSendRecordCreator request2Dto(SmsSendRecordCreateRequest request);

    SmsSendRecordQuery request2Query(SmsSendRecordQueryRequest request);

    SmsSendRecord queryToEntity(SmsSendRecordQuery query);

    SmsSendRecordResponse vo2Response(SmsSendRecordVO vo);

    default SmsSendRecordResponse vo2CustomResponse(SmsSendRecordVO vo) {
        SmsSendRecordResponse response = vo2Response(vo);
        return response;
    }
}
