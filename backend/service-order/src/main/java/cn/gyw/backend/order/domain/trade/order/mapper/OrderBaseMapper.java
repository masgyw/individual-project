// ---Auto Generated ---
package cn.gyw.backend.order.domain.trade.order.mapper;

import cn.gyw.backend.order.api.request.OrderBaseCreateRequest;
import cn.gyw.backend.order.api.request.OrderBaseQueryRequest;
import cn.gyw.backend.order.api.request.OrderBaseUpdateRequest;
import cn.gyw.backend.order.api.response.OrderBaseResponse;
import cn.gyw.backend.order.converter.OrderMapper;
import cn.gyw.backend.order.domain.trade.order.OrderBase;
import cn.gyw.backend.order.domain.trade.order.creator.OrderBaseCreator;
import cn.gyw.backend.order.domain.trade.order.query.OrderBaseQuery;
import cn.gyw.backend.order.domain.trade.order.updater.OrderBaseUpdater;
import cn.gyw.backend.order.domain.trade.order.vo.OrderBaseVO;
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
public interface OrderBaseMapper {
    OrderBaseMapper INSTANCE = Mappers.getMapper(OrderBaseMapper.class);

    OrderBase dtoToEntity(OrderBaseCreator dto);

    OrderBaseUpdater request2Updater(OrderBaseUpdateRequest request);

    OrderBaseCreator request2Dto(OrderBaseCreateRequest request);

    OrderBaseQuery request2Query(OrderBaseQueryRequest request);

    OrderBase queryToEntity(OrderBaseQuery query);

    OrderBaseResponse vo2Response(OrderBaseVO vo);

    default OrderBaseResponse vo2CustomResponse(OrderBaseVO vo) {
        OrderBaseResponse response = vo2Response(vo);
        return response;
    }
}
