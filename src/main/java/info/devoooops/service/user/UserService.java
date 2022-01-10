package info.devoooops.service.user;

import info.devoooops.entity.user.User;
import info.devoooops.payload.user.UserDto;

import java.util.Optional;

public interface UserService{
    Optional<User> signUpUser(UserDto.SignUpRequest request) throws Exception;
    Boolean checkDuplicate(String userId) throws Exception;
}
