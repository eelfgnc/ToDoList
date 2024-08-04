package com.elifgenc.service.utils.commandlinerunner;

import com.elifgenc.service.constant.RoleConstant;
import com.elifgenc.service.data.entity.Role;
import com.elifgenc.service.data.entity.User;
import com.elifgenc.service.data.entity.UserRole;
import com.elifgenc.service.data.repository.RoleRepository;
import com.elifgenc.service.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class MainRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void start(){
        log.info("Starting MainRunner");
    }

    @Bean
    public CommandLineRunner todoListApp() {
        return (args) -> {
            Role userPermissionRole = new Role();
            userPermissionRole.setName(RoleConstant.USER.getRole());
            Role upateUserRole = roleRepository.save(userPermissionRole);

            Role adminPermissionRole = new Role();
            adminPermissionRole.setName(RoleConstant.ADMIN.getRole());
            Role upateAdminRole = roleRepository.save(adminPermissionRole);

            User user = User
                    .builder()
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin+123"))
                    .firstName("Admin")
                    .lastName("Admin")
                    .city("Eskişehir")
                    .phone("5555555555")
                    .isLocked(false)
                    .isDeleted(false)
                    .lastLoaginDate(LocalDateTime.now())
                    .build();

            UserRole userRole = new UserRole();
            userRole.setRole(upateAdminRole);
            userRole.setUser(user);

            user.setUserRoles(List.of(userRole));
            userRepository.save(user);

        };
    }

    public void stop(){
        log.info("Stopping MainRunner");
    }
}
