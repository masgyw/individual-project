package cn.gyw.individual.starters.security.exception;

import org.springframework.security.core.AuthenticationException;

public class MethodNotSupportException extends AuthenticationException {
    public MethodNotSupportException(String msg) {
        super(msg);
    }
}
