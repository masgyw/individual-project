// ---Auto Generated ---
package cn.gyw.backend.system.api.response;

import cn.gyw.individual.commons.enums.AccountType;
import cn.gyw.individual.commons.enums.ValidStatus;
import cn.gyw.individual.commons.model.AbstractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class UserAccountResponse extends AbstractResponse {
  @Schema(
      title = "用户ID"
  )
  private Long userId;

  @Schema(
      title = "账号"
  )
  private String accountNo;

  @Schema(
      title = "密码"
  )
  private String password;

  @Schema(
      title = "账号类型"
  )
  private AccountType accountType;

  @Schema(
      title = "validStatus"
  )
  private ValidStatus validStatus;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  public ValidStatus getValidStatus() {
    return validStatus;
  }

  public void setValidStatus(ValidStatus validStatus) {
    this.validStatus = validStatus;
  }
}
