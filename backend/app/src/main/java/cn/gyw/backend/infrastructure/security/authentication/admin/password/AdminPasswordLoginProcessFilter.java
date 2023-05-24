package cn.gyw.backend.infrastructure.security.authentication.admin.password;

/**
 * @date 2023/5/23
 */

import cn.gyw.backend.infrastructure.security.AuthUrlEnum;
import cn.gyw.individual.starters.security.base.CustomAuthenticationFailureHandler;
import cn.gyw.individual.starters.security.base.CustomAuthenticationSuccessHandler;
import cn.gyw.individual.starters.security.base.PasswordLoginRequest;
import cn.gyw.individual.starters.security.config.AuthErrorMsg;
import cn.gyw.individual.starters.security.exception.MethodNotSupportException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台登录用户拦截器 ，组装登录请求，并提交给认证中心
 */
@Slf4j
public class AdminPasswordLoginProcessFilter extends AbstractAuthenticationProcessingFilter {

    private final CustomAuthenticationFailureHandler failureHandler;

    private final CustomAuthenticationSuccessHandler successHandler;

    public AdminPasswordLoginProcessFilter(AuthenticationManager authenticationManager,
                                           CustomAuthenticationFailureHandler failureHandler,
                                           CustomAuthenticationSuccessHandler successHandler) {
        super(AuthUrlEnum.ADMIN_PASS.getUrl());
        setAuthenticationManager(authenticationManager);
        this.failureHandler = failureHandler;
        this.successHandler = successHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("前端请求url:{}，uri:{}", request.getRequestURL(), request.getRequestURI());
        checkMethod(request);
        ObjectMapper mapper = new ObjectMapper();
        PasswordLoginRequest loginRequest = mapper.readValue(request.getReader(),
                PasswordLoginRequest.class);
        AdminUsernamePasswordToken token = new AdminUsernamePasswordToken(null, loginRequest.getUsername(), loginRequest.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    public void checkMethod(HttpServletRequest request) {
        if (!HttpMethod.POST.name().equals(request.getMethod())) {
            throw new MethodNotSupportException(AuthErrorMsg.methodNotSupport.getName());
        }
    }
}
