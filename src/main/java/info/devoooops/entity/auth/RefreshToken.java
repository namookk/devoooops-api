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
@Table(name="REFRESH_TOKEN")
public class RefreshToken {
    @Id
    @ApiParam(value = "cid", required = true)
    private String cid;

    @Column(name = "refresh_token", nullable = false)
    @ApiParam(value = "리프레쉬 토큰", required = true)
    private String refreshToken;

    public void updateToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
