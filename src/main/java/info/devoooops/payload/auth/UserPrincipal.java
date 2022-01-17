package info.devoooops.payload.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import info.devoooops.entity.user.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"username", "password", "accountNonExpired",
        "accountNonLocked", "credentialsNonExpired", "authorities", "enabled", "passwordDate, tempPasswordFl"})
public class UserPrincipal implements UserDetails, Serializable {

    private static final long serialVersionUID = -7818242887021456522L;
    private String cid;

    private String userId;

    private String name;

    private String nickname;

    private String gender;

    private UserStatus status;

    private String password;

    @JsonSerialize(using = InstantSerializer.class)
    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant passwordDate;

    private String birthDate;

    private String devField;

    private Integer career;

    private String profilePath;

    private String profileImgnm;

    private String tempPasswordFl;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserPrincipal fromToken(LinkedHashMap<String, Object> map){
        return UserPrincipal.builder()
                .cid((String)map.get("cid"))
                .userId((String)map.get("userId"))
                .name((String)map.get("name"))
                .nickname((String)map.get("nickname"))
                .status(UserStatus.valueOf((String)map.get("status")))
                .gender((String)map.get("gender"))
                .birthDate((String)map.get("birthDate"))
                .build();
    }
}
