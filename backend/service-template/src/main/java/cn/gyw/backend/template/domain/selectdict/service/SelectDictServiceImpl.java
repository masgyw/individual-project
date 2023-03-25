// ---Auto Generated ---
package cn.gyw.backend.template.domain.selectdict.service;

import cn.gyw.backend.template.domain.selectdict.SelectDict;
import cn.gyw.backend.template.domain.selectdict.creator.SelectDictCreator;
import cn.gyw.backend.template.domain.selectdict.mapper.SelectDictMapper;
import cn.gyw.backend.template.domain.selectdict.query.SelectDictQuery;
import cn.gyw.backend.template.domain.selectdict.repository.SelectDictRepository;
import cn.gyw.backend.template.domain.selectdict.updater.SelectDictUpdater;
import cn.gyw.backend.template.domain.selectdict.vo.SelectDictVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.starters.jpa.support.EntityOperations;
import java.lang.Long;
import java.lang.Override;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class SelectDictServiceImpl implements ISelectDictService {
  private final SelectDictRepository selectDictRepository;

  /**
   * createImpl
   */
  @Override
  public Long createSelectDict(SelectDictCreator creator) {
    Optional<SelectDict> selectDict = EntityOperations.doCreate(selectDictRepository)
    .create(() -> SelectDictMapper.INSTANCE.dtoToEntity(creator))
    .update(e -> e.init())
    .execute();
    return selectDict.isPresent() ? selectDict.get().getId() : 0;
  }

  /**
   * update
   */
  @Override
  public void updateSelectDict(SelectDictUpdater updater) {
    EntityOperations.doUpdate(selectDictRepository)
    .loadById(updater.getId())
    .update(e -> updater.updateSelectDict(e))
    .execute();
  }

  /**
   * valid
   */
  @Override
  public void validSelectDict(Long id) {
    EntityOperations.doUpdate(selectDictRepository)
    .loadById(id)
    .update(e -> e.valid())
    .execute();
  }

  /**
   * invalid
   */
  @Override
  public void invalidSelectDict(Long id) {
    EntityOperations.doUpdate(selectDictRepository)
    .loadById(id)
    .update(e -> e.invalid())
    .execute();
  }

  /**
   * findById
   */
  @Override
  public SelectDictVO findById(Long id) {
    Optional<SelectDict> selectDict =  selectDictRepository.findById(id);
    return new SelectDictVO(selectDict.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
  }

  /**
   * findByPage
   */
  @Override
  public Page<SelectDictVO> findByPage(PageRequestWrapper<SelectDictQuery> query) {
    Pageable pageable = PageRequest.of(query.getPage() - 1, query.getPageSize(), Sort.by(
                Sort.Direction.DESC, "createdAt"));
    Example<SelectDict> example = Example.of(SelectDictMapper.INSTANCE.queryToEntity(query.getBean()));
    Page<SelectDict> page = selectDictRepository.findAll(example, pageable);
    return new PageImpl<>(page.getContent().stream().map(entity -> new SelectDictVO(entity))
            .collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
  }
}
