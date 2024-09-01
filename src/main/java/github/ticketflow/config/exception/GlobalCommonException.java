package github.ticketflow.config.exception;

import github.ticketflow.config.exception.auth.AuthErrorCode;

public class GlobalCommonException extends RuntimeException {

    private final AuthErrorCode authErrorCode;

    public GlobalCommonException(AuthErrorCode authErrorCode) {
        super(authErrorCode.getMessage());
        this.authErrorCode = authErrorCode;
    }

    public GlobalCommonException(AuthErrorCode authErrorCode, String message) {
        super(message);
        this.authErrorCode = authErrorCode;
    }

    public GlobalCommonException(AuthErrorCode authErrorCode, String message, Throwable cause) {
        super(message, cause);
        this.authErrorCode = authErrorCode;
    }

    public AuthErrorCode getErrorCode() {
        return authErrorCode;
    }
}
