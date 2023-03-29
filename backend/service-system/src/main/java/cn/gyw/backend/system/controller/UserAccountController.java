// ---Auto Generated ---
package cn.gyw.backend.system.controller;

import cn.gyw.backend.system.api.request.UserAccountCreateRequest;
import cn.gyw.backend.system.api.request.UserAccountQueryRequest;
import cn.gyw.backend.system.api.request.UserAccountUpdateRequest;
import cn.gyw.backend.system.domain.user.creator.UserAccountCreator;
import cn.gyw.backend.system.domain.user.mapper.UserAccountMapper;
import cn.gyw.backend.system.domain.user.query.UserAccountQuery;
import cn.gyw.backend.system.domain.user.service.UserAccountService;
import cn.gyw.backend.system.domain.user.updater.UserAccountUpdater;
import cn.gyw.backend.system.domain.user.vo.UserAccountVO;
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
@RequestMapping("userAccount/v1")
@RequiredArgsConstructor
public class UserAccountController {
  private final UserAccountService userAccountService;

  /**
   * createRequest
   */
  @PostMapping("createUserAccount")
  public DataResponse<Long> createUserAccount(@RequestBody UserAccountCreateRequest request) {
    UserAccountCreator creator = UserAccountMapper.INSTANCE.request2Dto(request);
    return DataResponse.success(userAccountService.createUserAccount(creator));
  }

  /**
   * update request
   */
  @PostMapping("updateUserAccount")
  public DataResponse<String> updateUserAccount(@RequestBody UserAccountUpdateRequest request) {
    UserAccountUpdater updater = UserAccountMapper.INSTANCE.request2Updater(request);
    userAccountService.updateUserAccount(updater);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * valid
   */
  @PostMapping("valid/{id}")
  public DataResponse<String> validUserAccount(@PathVariable Long id) {
    userAccountService.validUserAccount(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * invalid
   */
  @PostMapping("invalid/{id}")
  public DataResponse<String> invalidUserAccount(@PathVariable Long id) {
    userAccountService.invalidUserAccount(id);
    return DataResponse.success(CodeEnum.Success.getName());
  }

  /**
   * findById
   */
  @GetMapping("findById/{id}")
  public DataResponse<UserAccountVO> findById(@PathVariable Long id) {
    UserAccountVO vo = userAccountService.findById(id);
    return DataResponse.success(vo);
  }

  /**
   * findByPage request
   */
  @PostMapping("findByPage")
  public DataResponse<PageData<UserAccountVO>> findByPage(
      @RequestBody PageRequestWrapper<UserAccountQueryRequest> request) {
    PageRequestWrapper<UserAccountQuery> wrapper = new PageRequestWrapper<>();
    wrapper.setBean(UserAccountMapper.INSTANCE.request2Query(request.getBean()));
    wrapper.setSorts(request.getSorts());
        wrapper.setPageSize(request.getPageSize());
        wrapper.setPage(request.getPage());
    Page<UserAccountVO> page = userAccountService.findByPage(wrapper);
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
