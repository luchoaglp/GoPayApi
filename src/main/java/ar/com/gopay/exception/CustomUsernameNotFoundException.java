package ar.com.gopay.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomUsernameNotFoundException extends AuthenticationException {

    public CustomUsernameNotFoundException(String message) {
        super(message);
    }
}
