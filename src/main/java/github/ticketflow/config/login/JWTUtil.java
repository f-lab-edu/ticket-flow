package github.ticketflow.config.login;

import github.ticketflow.config.exception.auth.AuthErrorResponsive;
import github.ticketflow.config.exception.GlobalCommonException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JWTUtil {

    private final CustomUserDetailsService userDetailsService;

    @Value("${spring.jwt.secret}")
    private String secret;
    @Value("${spring.jwt.expiration-time}")
    private Long expiredMs;

    private SecretKey createSecretKey(String secret) {
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }


    public String createJwt(String username, Long id) {
        return  Jwts.builder()
                .claim("email", username)
                .claim("user_id", id.toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(createSecretKey(secret))
                .compact();
    }

    public boolean isExpired(String token) {
        boolean isExpired = Jwts
                .parser()
                .verifyWith(createSecretKey(secret))
                .build().parseSignedClaims(token)
                .getPayload().getExpiration()
                .before(new Date());

        if (isExpired) {
            throw new GlobalCommonException(AuthErrorResponsive.TOKEN_EXPIRED);
        }
        return isExpired;
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .verifyWith(createSecretKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("email", String.class);
    }

    public Authentication getAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
