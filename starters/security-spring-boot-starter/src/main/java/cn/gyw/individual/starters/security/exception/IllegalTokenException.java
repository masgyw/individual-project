package cn.gyw.individual.starters.security.exception;

import org.springframework.security.core.AuthenticationException;

public class IllegalTokenException extends AuthenticationException {
    public IllegalTokenException(String msg) {
        super(msg);
    }
}
