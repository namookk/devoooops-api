package info.devoooops.payload.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -4722027617319630169L;

    @Schema(description = "권한유형", example = "Bearer")
    private String grantType;

    @Schema(description = "Access Token 값")
    private String accessToken;

    @Schema(description = "Access Token 만료기한")
    private long accessTokenExpiresIn;

    @Schema(description = "Refresh Token 값")
    private String refreshToken;

    @Schema(description = "비밀번호 변경 필요 여부")
    private String changePasswordFlag;

    public void setChangePasswordFlag(String changePasswordFlag){
        this.changePasswordFlag = changePasswordFlag;
    }

    @Override
    public String toString(){
        return "grantType : " + this.grantType +"\n"
            + "accessToken : " + this.accessToken +"\n"
            + "refreshToken : " + this.refreshToken +"\n"
            + "changePasswordFlag : " + this.changePasswordFlag +"\n";
    }
}
