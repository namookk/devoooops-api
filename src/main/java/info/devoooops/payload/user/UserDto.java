package info.devoooops.payload.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest{

        @NotEmpty
        @Schema(description = "회원 아이디(이메일)", example = "aaa@aaa.com")
        private String userId;

        @NotEmpty
        @Schema(description = "패스워드")
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpRequest {

        @NotEmpty
        @Email
        @Schema(description = "회원 아이디(이메일)", example = "aaa@aaa.com")
        private String userId;

        @NotEmpty
        @Schema(description = "패스워드")
        private String password;

        @NotEmpty
        @Schema(description = "이름", example = "홍길동")
        private String name;

        @NotEmpty
        @Schema(description = "닉네임", example = "길동쓰")
        private String nickname;

        //yyyy-mm-dd 형태를 가지는 패턴 조사
        @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$")
        @Schema(description = "생년월일", example = "1994-01-01")
        private String birthDate;

        @Pattern(regexp="[_M|F]{1}")
        @Schema(description = "성별", example = "F or M")
        private String gender;

        @Schema(description = "개발직군")
        private String devField;

        @Schema(description = "경력")
        private Integer career;

        @Schema(description = "프로필 이미지 파일")
        private MultipartFile profileImgFile;

        @Schema(description = "cid")
        private String cid;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeInfoRequest{

        @NotEmpty
        @Schema(description = "닉네임", example = "길동쓰")
        private String nickname;

        @Schema(description = "개발직군")
        private String devField;

        @Schema(description = "경력")
        private Integer career;

        @Schema(description = "프로필 이미지 파일")
        private MultipartFile profileImgFile;
    }

}
