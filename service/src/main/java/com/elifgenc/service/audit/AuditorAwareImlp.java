package com.elifgenc.service.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
public class AuditorAwareImlp implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            log.warn("Sistemde kullanıcı mevcut: " + authentication.getName());
            System.out.println("Sistemde kullanıcı mevcut: " + authentication.getName());
            log.warn("Sistemde kullanıcı mevcut: " + authentication.getPrincipal());
            return Optional.of(authentication.getName());
        }
        return Optional.of("ElifGenc");
    }
}
