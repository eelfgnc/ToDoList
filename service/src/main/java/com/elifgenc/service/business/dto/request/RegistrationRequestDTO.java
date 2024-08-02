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
@Schema(name = "Registration Request DTO", description = "Contains register information.")
public class RegistrationRequestDTO {

    @NotNull(message = "Firstname is null")
    @Schema(name = "firstName", description = "It is the first name of the user.", example = "Elif")
    private String firstName;

    @NotNull(message = "Lastname is null")
    @Schema(name = "lastName", description = "It is the last name of the user.", example = "Genç")
    private String lastName;

    @Schema(name = "city", description = "It is the the city where the user lives.", example = "Eskişehir")
    private String city;

    @Schema(name = "phone", description = "It is the phone of the user.", example = "5555555555")
    private String phone;

    @NotNull(message = "Email is mandatory")
    @Schema(name = "email", description = "It is the email of the user.", example = "test@gmail.com")
    private String email;

    @NotNull(message = "Password is not null")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    @Schema(name = "password", description = "It is the password of the user.", example = "*****")
    private String password;
}