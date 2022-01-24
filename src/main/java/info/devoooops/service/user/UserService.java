package info.devoooops.service.user;

import info.devoooops.common.error.exception.DevException;
import info.devoooops.common.error.exception.DevRuntimeException;
import info.devoooops.entity.user.User;
import info.devoooops.payload.auth.UserPrincipal;
import info.devoooops.payload.user.UserDto;

import java.util.Optional;

public interface UserService{

    /**
     * @return
     * @throws Exception
     */
    Optional<User> findById(String cid) throws Exception;
    /**
     * @param request
     * @return
     * @throws DevException
     */
    Optional<User> signUpUser(UserDto.SignUpRequest request) throws Exception;

    /**
     * @param userId
     * @return
     * @throws DevRuntimeException
     */
    Boolean checkDuplicate(String userId) throws DevRuntimeException;

    /**
     * @return
     * @throws Exception
     */
    Optional<User> getMyInfo() throws Exception;

    /**
     * @param password
     * @throws Exception
     */
    void checkPassword(String password) throws Exception;

    /**
     * @param userId
     * @param name
     * @throws Exception
     */
    void findPassword(String userId, String name) throws Exception;

    /**
     * @param password
     * @throws Exception
     */
    void changePassword(String password) throws Exception;

    /**
     * @param request
     * @return
     * @throws Exception
     */
    void changeMyInfo(UserDto.ChangeInfoRequest request) throws Exception;


    /**
     * @throws Exception
     */
    void withdrawUser() throws Exception;
}
