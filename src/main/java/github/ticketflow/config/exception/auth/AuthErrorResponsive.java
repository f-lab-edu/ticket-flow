package github.ticketflow.config.exception.auth;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorResponsive implements ErrorResponsive {

    // user error
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다"),
    FAIL_LOGIN(HttpStatus.BAD_REQUEST, "로그인을 할 수 없습니다."),
    FAIL_UPDATE(HttpStatus.BAD_REQUEST, "수정에 실패했습니다."),

    // token error
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    ABNORMAL_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 없거나 정상적이지 않은 토큰입니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
