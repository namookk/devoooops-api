package info.devoooops.payload.auth;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -4722027617319630169L;
    private String grantType;
    private String accessToken;
    private long accessTokenExpiresIn;
    private String refreshToken;

    @Override
    public String toString(){
        return "grantType : " + this.grantType +"\n"
            + "accessToken : " + this.accessToken +"\n"
            + "refreshToken : " + this.refreshToken +"\n";
    }
}
