package info.devoooops.service.user;

import info.devoooops.common.error.exception.DevException;
import info.devoooops.common.error.exception.DevRuntimeException;
import info.devoooops.entity.user.User;
import info.devoooops.payload.auth.UserPrincipal;
import info.devoooops.payload.user.UserDto;

import java.util.Optional;

public interface UserService{
    Optional<User> signUpUser(UserDto.SignUpRequest request) throws DevException;
    Boolean checkDuplicate(String userId) throws DevRuntimeException;
    Optional<UserPrincipal> getMyInfo() throws Exception;
    void checkPassword(String password) throws Exception;
}
