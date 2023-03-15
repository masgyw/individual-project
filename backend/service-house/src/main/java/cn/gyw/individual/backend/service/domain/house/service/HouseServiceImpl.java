// ---Auto Generated ---
package cn.gyw.individual.backend.service.domain.house.service;

import cn.gyw.individual.backend.service.domain.house.creator.HouseCreator;
import cn.gyw.individual.backend.service.domain.house.House;
import cn.gyw.individual.backend.service.domain.house.mapper.HouseMapper;
import cn.gyw.individual.backend.service.domain.house.query.HouseQuery;
import cn.gyw.individual.backend.service.domain.house.repository.HouseRepository;
import cn.gyw.individual.backend.service.domain.house.updater.HouseUpdater;
import cn.gyw.individual.backend.service.domain.house.vo.HouseVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public List<HouseVO> findByRange(HouseQuery query) {
        Specification<House> spec = (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(query.getStartCrawlDate())) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("crawlDate").as(LocalDate.class), query.getStartCrawlDate()));
            }
            if (Objects.nonNull(query.getEndCrawlDate())) {
                predicates.add(cb.lessThanOrEqualTo(root.get("crawlDate").as(LocalDate.class), query.getEndCrawlDate()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        List<House> dataList = houseRepository.findAll(spec);
        return dataList.stream().map(entity -> new HouseVO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public boolean batchInsert(List<HouseCreator> dataList) {
        Optional<Iterable<Iterable<House>>> optional = EntityOperations.doCreate(houseRepository)
                .batchCreate(() -> dataList.stream().map(HouseMapper.INSTANCE::dtoToEntity).collect(Collectors.toList()))
                .batchUpdate((list) -> list.forEach(House::init)).batchExecute();
        return optional.isPresent();
    }
}
