// ---Auto Generated ---
package cn.gyw.backend.template.domain.selectdict.mapper;

import cn.gyw.backend.template.api.request.SelectDictCreateRequest;
import cn.gyw.backend.template.api.request.SelectDictQueryRequest;
import cn.gyw.backend.template.api.request.SelectDictUpdateRequest;
import cn.gyw.backend.template.api.response.SelectDictResponse;
import cn.gyw.backend.template.domain.selectdict.SelectDict;
import cn.gyw.backend.template.domain.selectdict.creator.SelectDictCreator;
import cn.gyw.backend.template.domain.selectdict.query.SelectDictQuery;
import cn.gyw.backend.template.domain.selectdict.updater.SelectDictUpdater;
import cn.gyw.backend.template.domain.selectdict.vo.SelectDictVO;
import cn.gyw.backend.template.mapper.CustomMapper;
import cn.gyw.individual.commons.mapper.DateMapper;
import cn.gyw.individual.commons.mapper.GenericEnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                GenericEnumMapper.class,
                DateMapper.class,
                CustomMapper.class
        }
)
public interface SelectDictMapper {
    SelectDictMapper INSTANCE = Mappers.getMapper(SelectDictMapper.class);

    SelectDict dtoToEntity(SelectDictCreator dto);

    SelectDictUpdater request2Updater(SelectDictUpdateRequest request);

    SelectDictCreator request2Dto(SelectDictCreateRequest request);

    SelectDictQuery request2Query(SelectDictQueryRequest request);

    SelectDict queryToEntity(SelectDictQuery query);

    SelectDictResponse vo2Response(SelectDictVO vo);

    default SelectDictResponse vo2CustomResponse(SelectDictVO vo) {
        SelectDictResponse response = vo2Response(vo);
        return response;
    }
}
