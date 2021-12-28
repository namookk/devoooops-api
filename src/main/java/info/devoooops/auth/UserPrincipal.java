package info.devoooops.auth;

import info.devoooops.user.entity.User;
import info.devoooops.user.entity.UserStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Builder
public class UserPrincipal implements UserDetails {
    private String cid;
    private String userId;
    private String name;
    private String nickname;
    private String gender;
    private UserStatus status;
    private String password;
    private Instant passwordDate;
    private String birthDate;
    private String devField;
    private Integer career;
    private String profilePath;
    private String profileImgnm;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
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
}
