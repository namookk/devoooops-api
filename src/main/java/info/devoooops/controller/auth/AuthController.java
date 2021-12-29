package info.devoooops.controller.auth;

import info.devoooops.entity.auth.RefreshToken;
import info.devoooops.payload.auth.JwtRequest;
import info.devoooops.payload.auth.JwtResponse;
import info.devoooops.payload.auth.UserPrincipal;
import info.devoooops.payload.user.UserDto;
import info.devoooops.repository.auth.RefreshTokenRepository;
import info.devoooops.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "로그인", description = "사용자 로그인")
    @Parameters({
            @Parameter(name = "userId", description = "회원 아이디(이메일)", required = true),
            @Parameter(name = "password", description = "비밀번호", required = true)
    })
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> doLogin(@RequestBody UserDto.LoginRequest authenticationRequest) throws Exception{
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

        // 4. refresh token 저장
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        RefreshToken refreshToken = RefreshToken.builder()
                .cid(principal.getCid())
                .refreshToken(response.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "토큰 재발급", description = "토큰 재발급")
    @Parameters({
            @Parameter(name = "accessToken", description = "기존에 발급받은 Access Token 값", required = true),
            @Parameter(name = "refreshToken", description = "기존에 발급받은 Refresh Token 값", required = true)
    })
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> doLogin(@RequestBody JwtRequest tokenRequestDto) throws Exception{
        // 1. Refresh Token 검증
        if (!jwtTokenUtil.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = jwtTokenUtil.getAuthentication(tokenRequestDto.getAccessToken());
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findById(principal.getCid())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getRefreshToken().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        JwtResponse response = jwtTokenUtil.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        refreshToken.updateToken(response.getRefreshToken());

        // 토큰 발급
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
