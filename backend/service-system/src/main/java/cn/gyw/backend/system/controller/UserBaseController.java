// ---Auto Generated ---
package cn.gyw.backend.system.controller;

import cn.gyw.backend.system.api.request.UserBaseCreateRequest;
import cn.gyw.backend.system.api.request.UserBaseQueryRequest;
import cn.gyw.backend.system.api.request.UserBaseUpdateRequest;
import cn.gyw.backend.system.domain.user.creator.UserBaseCreator;
import cn.gyw.backend.system.domain.user.mapper.UserBaseMapper;
import cn.gyw.backend.system.domain.user.query.UserBaseQuery;
import cn.gyw.backend.system.domain.user.service.UserBaseService;
import cn.gyw.backend.system.domain.user.updater.UserBaseUpdater;
import cn.gyw.backend.system.domain.user.vo.UserBaseVO;
import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.model.DataResponse;
import cn.gyw.individual.commons.model.PageData;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import java.lang.Long;
import java.lang.String;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("userBase/v1")
@RequiredArgsConstructor
public class UserBaseController {
  private final UserBaseService userBaseService;

  /**
   * createRequest
   */
  @PostMapping("createUserBase")
  public DataResponse<Long> createUserBase(@RequestBody UserBaseCreateRequest request) {
    UserBaseCreator creator = UserBaseMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(userBaseService.createUserBase(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateUserBase")
  public DataResponse<String> updateUserBase(@RequestBody UserBaseUpdateRequest request) {
    UserBaseUpdater updater = UserBaseMapper.INSTANCE.request2Updater(request);
    userBaseService.updateUserBase(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validUserBase(@PathVariable Long id) {
    userBaseService.validUserBase(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidUserBase(@PathVariable Long id) {
    userBaseService.invalidUserBase(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<UserBaseVO> findById(@PathVariable Long id) {
    UserBaseVO vo = userBaseService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<UserBaseVO>> findByPage(
      @RequestBody PageRequestWrapper<UserBaseQueryRequest> request) {
    PageRequestWrapper<UserBaseQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(UserBaseMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<UserBaseVO> page = userBaseService.findByPage(wrapper);
    return DataResponse.success(
            PageData.of(
                page.getContent(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.getTotalPages())
        );
  }
}
