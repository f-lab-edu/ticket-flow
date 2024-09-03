package github.ticketflow.config.login;

import github.ticketflow.config.exception.ErrorCode;
import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.auth.AuthErrorCode;
import github.ticketflow.domian.user.UserEntity;
import github.ticketflow.domian.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new GlobalCommonException(AuthErrorCode.NOT_FOUND_EMAIL));
        return new CustomUserDetails(userEntity);
    }
}
