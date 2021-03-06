package info.devoooops.controller.auth;

import info.devoooops.common.error.ErrorConst;
import info.devoooops.common.error.exception.DevException;
import info.devoooops.common.error.exception.DevInternalServerErrorException;
import info.devoooops.common.error.exception.DevUnauthorizedException;
import info.devoooops.payload.auth.JwtRequest;
import info.devoooops.payload.user.UserDto;
import info.devoooops.service.auth.AuthService;
import info.devoooops.service.user.UserService;
import info.devoooops.util.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "회원가입", description = "사용자 회원가입")
//    @Parameters({
//            @Parameter(name = "userId", description = "회원 아이디(이메일)", required = true),
//            @Parameter(name = "password", description = "비밀번호", required = true)
//    })
    @PostMapping("/signup")
    public ResponseEntity<ApiUtils.ApiResult<?>> doSignUp(@RequestBody @Valid UserDto.SignUpRequest signUpRequest) throws Exception{
        return ResponseEntity.ok(ApiUtils.success(userService.signUpUser(signUpRequest)
                .orElseThrow(() -> new DevInternalServerErrorException(ErrorConst.UNKNOWN_ERROR))));
    }

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "회원가입시 아이디 중복 체크", description = "아이디 중복 체크")
    @Parameters({
            @Parameter(name = "userId", description = "회원 아이디(이메일)", required = true),
    })
    @GetMapping("/check/duplicate")
    public ResponseEntity<ApiUtils.ApiResult<?>> checkDuplicate(@RequestParam String userId) throws DevException {
        userService.checkDuplicate(userId);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "로그인", description = "사용자 로그인")
    @Parameters({
            @Parameter(name = "userId", description = "회원 아이디(이메일)", required = true),
            @Parameter(name = "password", description = "비밀번호", required = true)
    })
    @PostMapping("/login")
    public ResponseEntity<ApiUtils.ApiResult<?>> doLogin(@RequestBody @Valid UserDto.LoginRequest authenticationRequest) throws Exception{
        return ResponseEntity.ok(ApiUtils.success(authService.doLogin(authenticationRequest)));
    }

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "로그아웃", description = "로그아웃")
    @Parameters({
            @Parameter(name = "accessToken", description = "기존에 발급받은 Access Token 값", required = true),
            @Parameter(name = "refreshToken", description = "기존에 발급받은 Refresh Token 값", required = true)
    })
    @PostMapping("/logout")
    public ResponseEntity<ApiUtils.ApiResult<?>> doLogout(@RequestBody JwtRequest tokenRequestDto) throws Exception{
        authService.doLogout(tokenRequestDto);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "토큰 재발급", description = "토큰 재발급")
    @Parameters({
            @Parameter(name = "accessToken", description = "기존에 발급받은 Access Token 값", required = true),
            @Parameter(name = "refreshToken", description = "기존에 발급받은 Refresh Token 값", required = true)
    })
    @PostMapping("/refresh")
    public ResponseEntity<ApiUtils.ApiResult<?>> reissueToken(@RequestBody JwtRequest tokenRequestDto) throws Exception{
        return ResponseEntity.ok(ApiUtils.success(authService.reissueToken(tokenRequestDto)));
    }

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "비밀번호 찾기", description = "비밀번호 찾기")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디(이메일)", required = true),
            @Parameter(name = "name", description = "유저 이름", required = true)
    })
    @PostMapping("/find/password")
    public ResponseEntity<ApiUtils.ApiResult<?>> findPassword(@RequestParam String userId,
                                                              @RequestParam String name) throws Exception{
        userService.findPassword(userId, name);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Tag(name="auth", description = "회원 권한 API")
    @Operation(summary = "권한 에러 처리 ", description = "권한 에러 처리 ")
    @GetMapping("/exception")
    public ResponseEntity<ApiUtils.ApiResult<?>> exceptionAccessDenied() throws DevUnauthorizedException {
        throw new DevUnauthorizedException(ErrorConst.REQUIRED_AUTH);
    }

}
