package info.devoooops.config;

import info.devoooops.entity.user.User;
import info.devoooops.payload.auth.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return () -> Optional.of(principal.getCid());
        }else{
            return () -> Optional.of("system");
        }
//        return () -> Optional.of("test");
    }
}
