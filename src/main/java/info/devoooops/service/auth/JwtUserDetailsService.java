package info.devoooops.service.auth;

import info.devoooops.entity.user.User;
import info.devoooops.entity.user.UserStatus;
import info.devoooops.model.transaction.DevTransactionRead;
import info.devoooops.repository.user.UserRepository;
import info.devoooops.payload.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @DevTransactionRead
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //탈퇴회원 검증
        if(user.getStatus().equals(UserStatus.N)){
            throw new UsernameNotFoundException("User not found");
        }

        UserPrincipal principal = modelMapper.map(user, UserPrincipal.class);
        return principal;
    }
}
