package github.ticketflow.config.exception;

public class GlobalCommonException extends RuntimeException {

    private final ErrorResponsive errorResponsive;

    public GlobalCommonException(ErrorResponsive errorResponsive) {
        super(errorResponsive.getMessage());
        this.errorResponsive = errorResponsive;
    }

    public GlobalCommonException(ErrorResponsive errorResponsive, String message) {
        super(message);
        this.errorResponsive = errorResponsive;
    }

    public GlobalCommonException(ErrorResponsive errorResponsive, String message, Throwable cause) {
        super(message, cause);
        this.errorResponsive = errorResponsive;
    }

    public ErrorResponsive getErrorCode() {
        return errorResponsive;
    }
}
