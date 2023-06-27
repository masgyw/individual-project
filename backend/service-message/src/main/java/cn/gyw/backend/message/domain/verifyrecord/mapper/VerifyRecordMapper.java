// ---Auto Generated ---
package cn.gyw.backend.message.domain.verifyrecord.mapper;

import cn.gyw.backend.message.api.request.VerifyRecordCreateRequest;
import cn.gyw.backend.message.api.request.VerifyRecordQueryRequest;
import cn.gyw.backend.message.api.request.VerifyRecordUpdateRequest;
import cn.gyw.backend.message.api.response.VerifyRecordResponse;
import cn.gyw.backend.message.domain.verifyrecord.VerifyRecord;
import cn.gyw.backend.message.domain.verifyrecord.creator.VerifyRecordCreator;
import cn.gyw.backend.message.domain.verifyrecord.query.VerifyRecordQuery;
import cn.gyw.backend.message.domain.verifyrecord.updater.VerifyRecordUpdater;
import cn.gyw.backend.message.domain.verifyrecord.vo.VerifyRecordVO;
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
public interface VerifyRecordMapper {
  VerifyRecordMapper INSTANCE = Mappers.getMapper(VerifyRecordMapper.class);

  VerifyRecord dtoToEntity(VerifyRecordCreator dto);

  VerifyRecordUpdater request2Updater(VerifyRecordUpdateRequest request);

  VerifyRecordCreator request2Dto(VerifyRecordCreateRequest request);

  VerifyRecordQuery request2Query(VerifyRecordQueryRequest request);

  VerifyRecord queryToEntity(VerifyRecordQuery query);

  VerifyRecordResponse vo2Response(VerifyRecordVO vo);

  default VerifyRecordResponse vo2CustomResponse(VerifyRecordVO vo) {
    VerifyRecordResponse response = vo2Response(vo);
    return response;
  }
}
