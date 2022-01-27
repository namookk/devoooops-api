package info.devoooops.controller.user;

import info.devoooops.common.error.ErrorConst;
import info.devoooops.common.error.exception.DevInternalServerErrorException;
import info.devoooops.entity.user.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @Tag(name="user", description = "회원 API")
    @Operation(summary = "비밀번호 확인", description = "비밀번호 확인")
    @Parameters({
            @Parameter(name = "password", description = "비밀번호", required = true),
    })
    @PostMapping("/check/password")
    public ResponseEntity<ApiUtils.ApiResult<?>> checkMyPassword(@RequestParam String password) throws Exception {
        userService.checkPassword(password);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Tag(name="user", description = "회원 API")
    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경")
    @Parameters({
            @Parameter(name = "password", description = "비밀번호", required = true),
    })
    @PutMapping("/change/password")
    public ResponseEntity<ApiUtils.ApiResult<?>> changeMyPassword(@RequestParam String password) throws Exception {
        userService.changePassword(password);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Tag(name="user", description = "회원 API")
    @Operation(summary = "회원정보 변경", description = "회원정보 변경")
    @Parameters({
            @Parameter(name = "password", description = "비밀번호", required = true),
    })
    @PutMapping("/change/info")
    public ResponseEntity<ApiUtils.ApiResult<?>> changeMyInfo(@RequestBody @Valid UserDto.ChangeInfoRequest request) throws Exception {
        userService.changeMyInfo(request);

        //변경된 내 정보 반환
        return ResponseEntity.ok(ApiUtils.success(userService.getMyInfo()
                .orElseThrow(() -> new DevInternalServerErrorException(ErrorConst.UNKNOWN_ERROR))));
    }


    @Tag(name="user", description = "회원 API")
    @Operation(summary = "회원탈퇴", description = "회원탈퇴")
    @DeleteMapping("/withdraw")
    public ResponseEntity<ApiUtils.ApiResult<?>> doWithdraw() throws Exception{
        User user = userService.withdrawUser();

        //토큰 삭제
        authService.deleteToken(user.getCid());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Tag(name="user", description = "회원 API")
    @Operation(summary = "내 정보 확인", description = "내 정보 확인")
    @GetMapping("/me")
    public ResponseEntity<ApiUtils.ApiResult<?>> findMyInfo(HttpServletRequest request) throws Exception {
        log.info("cid ==== > {}", request.getAttribute("cid"));
        return ResponseEntity.ok(ApiUtils.success(userService.getMyInfo()
                .orElseThrow(() -> new DevInternalServerErrorException(ErrorConst.UNKNOWN_ERROR))));
    }
}
