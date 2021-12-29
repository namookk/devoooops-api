package info.devoooops.payload.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = -7028798155701119466L;
    @Schema(description = "Access Token 값")
    private String accessToken;

    @Schema(description = "Refresh Token 값")
    private String refreshToken;
}
