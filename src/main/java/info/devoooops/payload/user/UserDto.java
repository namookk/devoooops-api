package info.devoooops.payload.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest implements Serializable {
        private static final long serialVersionUID = -4364185033878551027L;

        @Schema(description = "회원 아이디(이메일)", example = "aaa@aaa.com")
        private String userId;

        @Schema(description = "패스워드")
        private String password;
    }
}
