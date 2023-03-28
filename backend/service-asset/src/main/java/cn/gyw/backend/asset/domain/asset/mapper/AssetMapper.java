// ---Auto Generated ---
package cn.gyw.backend.asset.domain.asset.mapper;

import cn.gyw.backend.asset.api.request.AssetCreateRequest;
import cn.gyw.backend.asset.api.request.AssetQueryRequest;
import cn.gyw.backend.asset.api.request.AssetUpdateRequest;
import cn.gyw.backend.asset.api.response.AssetResponse;
import cn.gyw.backend.asset.domain.asset.Asset;
import cn.gyw.backend.asset.domain.asset.creator.AssetCreator;
import cn.gyw.backend.asset.domain.asset.query.AssetQuery;
import cn.gyw.backend.asset.domain.asset.updater.AssetUpdater;
import cn.gyw.backend.asset.domain.asset.vo.AssetVO;
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
public interface AssetMapper {
  AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);

  Asset dtoToEntity(AssetCreator dto);

  AssetUpdater request2Updater(AssetUpdateRequest request);

  AssetCreator request2Dto(AssetCreateRequest request);

  AssetQuery request2Query(AssetQueryRequest request);

  Asset queryToEntity(AssetQuery query);

  AssetResponse vo2Response(AssetVO vo);

  default AssetResponse vo2CustomResponse(AssetVO vo) {
    AssetResponse response = vo2Response(vo);
    return response;
  }
}
