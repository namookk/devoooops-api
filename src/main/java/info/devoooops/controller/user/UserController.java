package info.devoooops.controller.user;

import info.devoooops.entity.user.User;
import info.devoooops.payload.auth.UserPrincipal;
import info.devoooops.service.user.UserService;
import info.devoooops.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiUtils.ApiResult<?>> findMyInfo() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(ApiUtils.success(userPrincipal));
    }
}
