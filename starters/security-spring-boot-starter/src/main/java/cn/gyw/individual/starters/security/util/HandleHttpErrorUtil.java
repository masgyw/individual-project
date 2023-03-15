package cn.gyw.individual.starters.security.util;

import cn.gyw.individual.starters.security.exception.CustomAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Slf4j
public class HandleHttpErrorUtil {

    private final static Integer AUTH_ERROR_CODE = 403;

    private HandleHttpErrorUtil() {
    }

    public static void handleHttpError(HttpServletRequest request, HttpServletResponse response,
                                       AuthenticationException e) throws Exception {
        // 设置http 返回请求错误码
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        AuthResponse authResponse = new AuthResponse();
        if (CustomAuthenticationException.class.isAssignableFrom(e.getClass())) {
            CustomAuthenticationException cusException = (CustomAuthenticationException) e;
            authResponse.setCode(cusException.getCode());
            authResponse.setMsg(cusException.getMessage());
        } else {
            authResponse.setCode(AUTH_ERROR_CODE);
            authResponse.setMsg(e.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        response.getOutputStream().write(objectMapper.writeValueAsBytes(authResponse));
    }

    @Data
    static class AuthResponse implements Serializable {
        private Integer code;
        private String msg;
        private Object result;
    }
}
