package com.elifgenc.service.business.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(name = "Authentication Request DTO", description = "Contains login information.")
public class AuthenticationRequestDTO {

    @NotNull(message = "Email is not null.")
    @Schema(name = "email", description = "It is the email address of the user.", example = "test@gmail.com")
    private String email;

    @NotNull(message = "Password is not null.")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    @Schema(name = "password", description = "It is the password of the user.", example = "*****")
    private String password;

}