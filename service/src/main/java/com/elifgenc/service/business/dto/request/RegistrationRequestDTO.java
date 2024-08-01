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
public class RegistrationRequestDTO {

    @NotEmpty(message = "Firstname is empty")
    @NotNull(message = "Firstname is null")
    private String firstname;

    @NotEmpty(message = "Lastname is empty")
    @NotNull(message = "Lastname is null")
    private String lastname;

    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is null")
    @NotNull(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Password is not empty.")
    @NotNull(message = "Password is not null")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
}