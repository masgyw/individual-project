// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.orderlifecycle.mapper;

import cn.gyw.backend.order.api.request.OrderLifecycleCreateRequest;
import cn.gyw.backend.order.api.request.OrderLifecycleQueryRequest;
import cn.gyw.backend.order.api.request.OrderLifecycleUpdateRequest;
import cn.gyw.backend.order.api.response.OrderLifecycleResponse;
import cn.gyw.backend.order.converter.OrderMapper;
import cn.gyw.backend.order.domain.trade.orderlifecycle.OrderLifecycle;
import cn.gyw.backend.order.domain.trade.orderlifecycle.creator.OrderLifecycleCreator;
import cn.gyw.backend.order.domain.trade.orderlifecycle.query.OrderLifecycleQuery;
import cn.gyw.backend.order.domain.trade.orderlifecycle.updater.OrderLifecycleUpdater;
import cn.gyw.backend.order.domain.trade.orderlifecycle.vo.OrderLifecycleVO;
import cn.gyw.individual.commons.mapper.DateMapper;
import cn.gyw.individual.commons.mapper.GenericEnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                GenericEnumMapper.class,
                DateMapper.class,
                OrderMapper.class
        }
)
public interface OrderLifecycleMapper {
    OrderLifecycleMapper INSTANCE = Mappers.getMapper(OrderLifecycleMapper.class);

    OrderLifecycle dtoToEntity(OrderLifecycleCreator dto);

    OrderLifecycleUpdater request2Updater(OrderLifecycleUpdateRequest request);

    OrderLifecycleCreator request2Dto(OrderLifecycleCreateRequest request);

    OrderLifecycleQuery request2Query(OrderLifecycleQueryRequest request);

    OrderLifecycle queryToEntity(OrderLifecycleQuery query);

    OrderLifecycleResponse vo2Response(OrderLifecycleVO vo);

    default OrderLifecycleResponse vo2CustomResponse(OrderLifecycleVO vo) {
        OrderLifecycleResponse response = vo2Response(vo);
        return response;
    }
}
