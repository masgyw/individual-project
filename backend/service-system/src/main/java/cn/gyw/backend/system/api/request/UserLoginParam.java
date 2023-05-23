package cn.gyw.backend.system.api.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserLoginParam {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

}
