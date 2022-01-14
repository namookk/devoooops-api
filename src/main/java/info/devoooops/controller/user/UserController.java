package info.devoooops.controller.user;

import info.devoooops.common.error.ErrorConst;
import info.devoooops.common.error.exception.DevInternalServerErrorException;
import info.devoooops.entity.user.User;
import info.devoooops.payload.auth.UserPrincipal;
import info.devoooops.service.user.UserService;
import info.devoooops.util.ApiUtils;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
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
    @Operation(summary = "내 정보 확인", description = "내 정보 확인")
    @GetMapping("/me")
    public ResponseEntity<ApiUtils.ApiResult<?>> findMyInfo() throws Exception {
        return ResponseEntity.ok(ApiUtils.success(userService.getMyInfo()
                .orElseThrow(() -> new DevInternalServerErrorException(ErrorConst.UNKNOWN_ERROR))));
    }
}
