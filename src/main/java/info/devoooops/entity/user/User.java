package info.devoooops.entity.user;

import info.devoooops.model.audit.DateAudit;
import info.devoooops.payload.user.UserDto;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="USER")
public class User extends DateAudit {

    @Id
    @ApiParam(value = "CID", required = true)
    private String cid;

    @Column(name = "user_id", nullable = false)
    @ApiParam(value = "유저 아이디(이메일)", required = true)
    private String userId;

    @Column(name="password", nullable = false)
    @ApiParam(value = "비밀번호", required = true)
    private String password;

    @Column(name="password_date")
    @ApiParam(value = "비밀번호 변경날짜")
    private Instant passwordDate;

    @Column(name="name", nullable = false)
    @ApiParam(value = "이름", required = true)
    private String name;

    @Column(name="nickname", nullable = false)
    @ApiParam(value = "닉네임", required = true)
    private String nickname;

    @Column(name="birth_date", nullable = false)
    @ApiParam(value = "생일 (YYYY-MM-DD)", required = true)
    private String birthDate;

    @Column(name="gender", nullable = false)
    @ApiParam(value = "성별 (M - 남자, F - 여자)", required = true)
    private String gender;

    @Column(name="dev_field")
    @ApiParam(value = "개발분야")
    private String devField;

    @Column(name="career")
    @ApiParam(value = "경력")
    private Integer career;

    @Column(name="profile_path")
    @ApiParam(value = "프로필 이미지 패스")
    private String profilePath;

    @Column(name="profile_imgnm")
    @ApiParam(value = "프로필 이미지 명")
    private String profileImgnm;

    @Column(name="status", nullable = false)
    @ApiParam(value = "회원상태", required = true)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public static User fromSignUpRequest(UserDto.SignUpRequest request){
        User user = new User();
        user.cid = request.getCid();
        user.userId = request.getUserId();
        user.password = request.getPassword();
        user.passwordDate = Instant.now();
        user.name = request.getName();
        user.nickname = request.getNickname();
        user.birthDate = request.getBirthDate();
        user.gender = request.getGender();
        user.status = UserStatus.Y;
        user.devField = request.getDevField();
        user.career = request.getCareer();

        return user;
    }
}
