// ---Auto Generated ---
package cn.gyw.backend.system.domain.permission.resource.mapper;

import cn.gyw.backend.system.api.request.ResourceCreateRequest;
import cn.gyw.backend.system.api.request.ResourceQueryRequest;
import cn.gyw.backend.system.api.request.ResourceUpdateRequest;
import cn.gyw.backend.system.api.response.ResourceResponse;
import cn.gyw.backend.system.api.response.ResourceTree;
import cn.gyw.backend.system.domain.permission.resource.Resource;
import cn.gyw.backend.system.domain.permission.resource.creator.ResourceCreator;
import cn.gyw.backend.system.domain.permission.resource.query.ResourceQuery;
import cn.gyw.backend.system.domain.permission.resource.updater.ResourceUpdater;
import cn.gyw.backend.system.domain.permission.resource.vo.ResourceVO;
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
public interface ResourceMapper {
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    Resource dtoToEntity(ResourceCreator dto);

    ResourceUpdater request2Updater(ResourceUpdateRequest request);

    ResourceCreator request2Dto(ResourceCreateRequest request);

    ResourceQuery request2Query(ResourceQueryRequest request);

    Resource queryToEntity(ResourceQuery query);

    ResourceResponse vo2Response(ResourceVO vo);

    ResourceTree entityToTree(Resource resource);

    default ResourceResponse vo2CustomResponse(ResourceVO vo) {
        ResourceResponse response = vo2Response(vo);
        return response;
    }
}
