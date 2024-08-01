package com.elifgenc.service.business.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequestDTO {

    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is not empty.")
    @NotNull(message = "Email is not null.")
    private String email;

    @NotEmpty(message = "Password is empty.")
    @NotNull(message = "Password is not null.")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;

}