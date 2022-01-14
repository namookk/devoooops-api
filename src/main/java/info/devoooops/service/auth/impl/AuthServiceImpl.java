package info.devoooops.service.auth.impl;

import info.devoooops.entity.auth.AuthToken;
import info.devoooops.entity.user.User;
import info.devoooops.payload.auth.JwtRequest;
import info.devoooops.payload.auth.JwtResponse;
import info.devoooops.payload.auth.UserPrincipal;
import info.devoooops.payload.user.UserDto;
import info.devoooops.repository.auth.AuthTokenRepository;
import info.devoooops.service.auth.AuthService;
import info.devoooops.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthTokenRepository authTokenRepository;

    @Override
    public JwtResponse doLogin(UserDto.LoginRequest authenticationRequest) throws Exception {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserId(),
                authenticationRequest.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtResponse response = jwtTokenUtil.generateTokenDto(authentication);

        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        String cid = principal.getCid();

        // 4. token db 저장
        AuthToken authToken = AuthToken.builder()
                .cid(cid)
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .build();

        authTokenRepository.save(authToken);

        return response;
    }

    @Override
    public void doLogout(JwtRequest tokenRequestDto) throws Exception {
        if (!jwtTokenUtil.validateToken(tokenRequestDto.getAccessToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }
        Authentication authentication = jwtTokenUtil.getAuthentication(tokenRequestDto.getAccessToken());
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        String cid = principal.getCid();
        authTokenRepository.deleteById(cid);
    }

    @Override
    public JwtResponse reissueToken(JwtRequest tokenRequestDto) throws Exception {
        // 1. Refresh Token 검증
        if (!jwtTokenUtil.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = jwtTokenUtil.getAuthentication(tokenRequestDto.getAccessToken());
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        AuthToken authToken = authTokenRepository.findById(principal.getCid())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!authToken.getRefreshToken().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        JwtResponse response = jwtTokenUtil.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        authToken.updateToken(response.getAccessToken(), response.getRefreshToken());

        return response;
    }
}
