// ---Auto Generated ---
package cn.gyw.individual.backend.service.service;

import cn.gyw.individual.backend.service.creator.HouseCreator;
import cn.gyw.individual.backend.service.domain.House;
import cn.gyw.individual.backend.service.mapper.HouseMapper;
import cn.gyw.individual.backend.service.query.HouseQuery;
import cn.gyw.individual.backend.service.repository.HouseRepository;
import cn.gyw.individual.backend.service.updater.HouseUpdater;
import cn.gyw.individual.backend.service.vo.HouseVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class HouseServiceImpl implements IHouseService {
    private final HouseRepository houseRepository;

    /**
     * createImpl
     */
    @Override
    public Long createHouse(HouseCreator creator) {
        Optional<House> house = EntityOperations.doCreate(houseRepository)
                .create(() -> HouseMapper.INSTANCE.dtoToEntity(creator))
                .update(e -> e.init())
                .execute();
        return house.isPresent() ? house.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updateHouse(HouseUpdater updater) {
        EntityOperations.doUpdate(houseRepository)
                .loadById(updater.getId())
                .update(e -> updater.updateHouse(e))
                .execute();
    }

    /**
     * valid
     */
    @Override
    public void validHouse(Long id) {
        EntityOperations.doUpdate(houseRepository)
                .loadById(id)
                .update(e -> e.valid())
                .execute();
    }

    /**
     * invalid
     */
    @Override
    public void invalidHouse(Long id) {
        EntityOperations.doUpdate(houseRepository)
                .loadById(id)
                .update(e -> e.invalid())
                .execute();
    }

    /**
     * findById
     */
    @Override
    public HouseVO findById(Long id) {
        Optional<House> house = houseRepository.findById(id);
        return new HouseVO(house.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public Page<HouseVO> findByPage(PageRequestWrapper<HouseQuery> query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
        Example<House> example = Example.of(HouseMapper.INSTANCE.queryToEntity(query.getBean()));
        Page<House> page = houseRepository.findAll(example, pageable);
        return new PageImpl<>(page.getContent().stream().map(entity -> new HouseVO(entity))
                .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    @Override
    public boolean batchInsert(List<HouseCreator> dataList) {
        EntityOperations.doCreate(houseRepository)
                .create(() -> dataList.stream().map(HouseMapper.INSTANCE::dtoToEntity).collect(Collectors.toList()))
        houseRepository.saveAll()
        return false;
    }
}
