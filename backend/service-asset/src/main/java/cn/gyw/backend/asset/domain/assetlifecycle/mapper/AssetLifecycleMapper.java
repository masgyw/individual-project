// ---Auto Generated ---
package cn.gyw.backend.asset.domain.assetlifecycle.mapper;

import cn.gyw.backend.asset.api.request.AssetLifecycleCreateRequest;
import cn.gyw.backend.asset.api.request.AssetLifecycleQueryRequest;
import cn.gyw.backend.asset.api.request.AssetLifecycleUpdateRequest;
import cn.gyw.backend.asset.api.response.AssetLifecycleResponse;
import cn.gyw.backend.asset.domain.assetlifecycle.AssetLifecycle;
import cn.gyw.backend.asset.domain.assetlifecycle.creator.AssetLifecycleCreator;
import cn.gyw.backend.asset.domain.assetlifecycle.query.AssetLifecycleQuery;
import cn.gyw.backend.asset.domain.assetlifecycle.updater.AssetLifecycleUpdater;
import cn.gyw.backend.asset.domain.assetlifecycle.vo.AssetLifecycleVO;
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
public interface AssetLifecycleMapper {
  AssetLifecycleMapper INSTANCE = Mappers.getMapper(AssetLifecycleMapper.class);

  AssetLifecycle dtoToEntity(AssetLifecycleCreator dto);

  AssetLifecycleUpdater request2Updater(AssetLifecycleUpdateRequest request);

  AssetLifecycleCreator request2Dto(AssetLifecycleCreateRequest request);

  AssetLifecycleQuery request2Query(AssetLifecycleQueryRequest request);

  AssetLifecycle queryToEntity(AssetLifecycleQuery query);

  AssetLifecycleResponse vo2Response(AssetLifecycleVO vo);

  default AssetLifecycleResponse vo2CustomResponse(AssetLifecycleVO vo) {
    AssetLifecycleResponse response = vo2Response(vo);
    return response;
  }
}
