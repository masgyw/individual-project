// ---Auto Generated ---
package cn.gyw.backend.asset.domain.assetrecord.mapper;

import cn.gyw.backend.asset.api.request.AssetInOutRecordCreateRequest;
import cn.gyw.backend.asset.api.request.AssetInOutRecordQueryRequest;
import cn.gyw.backend.asset.api.request.AssetInOutRecordUpdateRequest;
import cn.gyw.backend.asset.api.response.AssetInOutRecordResponse;
import cn.gyw.backend.asset.domain.assetrecord.AssetInOutRecord;
import cn.gyw.backend.asset.domain.assetrecord.creator.AssetInOutRecordCreator;
import cn.gyw.backend.asset.domain.assetrecord.query.AssetInOutRecordQuery;
import cn.gyw.backend.asset.domain.assetrecord.updater.AssetInOutRecordUpdater;
import cn.gyw.backend.asset.domain.assetrecord.vo.AssetInOutRecordVO;
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
public interface AssetInOutRecordMapper {
  AssetInOutRecordMapper INSTANCE = Mappers.getMapper(AssetInOutRecordMapper.class);

  AssetInOutRecord dtoToEntity(AssetInOutRecordCreator dto);

  AssetInOutRecordUpdater request2Updater(AssetInOutRecordUpdateRequest request);

  AssetInOutRecordCreator request2Dto(AssetInOutRecordCreateRequest request);

  AssetInOutRecordQuery request2Query(AssetInOutRecordQueryRequest request);

  AssetInOutRecord queryToEntity(AssetInOutRecordQuery query);

  AssetInOutRecordResponse vo2Response(AssetInOutRecordVO vo);

  default AssetInOutRecordResponse vo2CustomResponse(AssetInOutRecordVO vo) {
    AssetInOutRecordResponse response = vo2Response(vo);
    return response;
  }
}
