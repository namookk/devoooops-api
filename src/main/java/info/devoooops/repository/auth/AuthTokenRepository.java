package info.devoooops.repository.auth;

import info.devoooops.entity.auth.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {
    Optional<AuthToken> findByAccessToken(String accessToken);
    Optional<AuthToken> findByRefreshToken(String refreshToken);
}
