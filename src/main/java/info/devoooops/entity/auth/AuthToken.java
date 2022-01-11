package info.devoooops.entity.auth;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="AUTH_TOKEN")
public class AuthToken {
    @Id
    @ApiParam(value = "cid", required = true)
    private String cid;

    @Column(name = "access_token", nullable = false)
    @ApiParam(value = "어세스 토큰", required = true)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false)
    @ApiParam(value = "리프레쉬 토큰", required = true)
    private String refreshToken;

    public void updateToken(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
