// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.reviserecord.mapper;

import cn.gyw.backend.order.api.request.ReviseRecordCreateRequest;
import cn.gyw.backend.order.api.request.ReviseRecordQueryRequest;
import cn.gyw.backend.order.api.request.ReviseRecordUpdateRequest;
import cn.gyw.backend.order.api.response.ReviseRecordResponse;
import cn.gyw.backend.order.domain.trade.reviserecord.ReviseRecord;
import cn.gyw.backend.order.domain.trade.reviserecord.creator.ReviseRecordCreator;
import cn.gyw.backend.order.domain.trade.reviserecord.query.ReviseRecordQuery;
import cn.gyw.backend.order.domain.trade.reviserecord.updater.ReviseRecordUpdater;
import cn.gyw.backend.order.domain.trade.reviserecord.vo.ReviseRecordVO;
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
public interface ReviseRecordMapper {
  ReviseRecordMapper INSTANCE = Mappers.getMapper(ReviseRecordMapper.class);

  ReviseRecord dtoToEntity(ReviseRecordCreator dto);

  ReviseRecordUpdater request2Updater(ReviseRecordUpdateRequest request);

  ReviseRecordCreator request2Dto(ReviseRecordCreateRequest request);

  ReviseRecordQuery request2Query(ReviseRecordQueryRequest request);

  ReviseRecord queryToEntity(ReviseRecordQuery query);

  ReviseRecordResponse vo2Response(ReviseRecordVO vo);

  default ReviseRecordResponse vo2CustomResponse(ReviseRecordVO vo) {
    ReviseRecordResponse response = vo2Response(vo);
    return response;
  }
}
