package info.devoooops.auth;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -4722027617319630169L;
    private String grantType;
    private String accessToken;
    private long accessTokenExpiresIn;
    private String refreshToken;
}
