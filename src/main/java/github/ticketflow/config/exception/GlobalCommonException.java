package github.ticketflow.config.exception;

import github.ticketflow.config.exception.auth.AuthErrorCode;

public class GlobalCommonException extends RuntimeException {

    private final ErrorCode errorCode;

    public GlobalCommonException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public GlobalCommonException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public GlobalCommonException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
