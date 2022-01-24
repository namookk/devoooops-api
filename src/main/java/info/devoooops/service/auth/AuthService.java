package info.devoooops.service.auth;

import info.devoooops.payload.auth.JwtRequest;
import info.devoooops.payload.auth.JwtResponse;
import info.devoooops.payload.user.UserDto;

/**
 * @author namookk
 * 권한 관리 서비스
 */
public interface AuthService {

    /**
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    JwtResponse doLogin(UserDto.LoginRequest authenticationRequest) throws Exception;

    /**
     * @param tokenRequestDto
     * @throws Exception
     */
    void doLogout(JwtRequest tokenRequestDto) throws Exception;

    /**
     * @param tokenRequestDto
     * @return
     * @throws Exception
     */
    JwtResponse reissueToken(JwtRequest tokenRequestDto) throws Exception;

    /**
     * @param cid
     * @throws Exception
     */
    void deleteToken(String cid) throws Exception;
}
