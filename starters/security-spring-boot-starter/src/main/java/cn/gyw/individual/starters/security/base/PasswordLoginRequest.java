package cn.gyw.individual.starters.security.base;

import lombok.Data;

@Data
public class PasswordLoginRequest {

  private String username;

  private String password;

  private boolean forceLogin;
}
