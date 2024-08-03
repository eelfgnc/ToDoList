package com.elifgenc.service.business.dto.response;

import com.elifgenc.service.data.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@Schema(name = "User Information DTO", description = "It includes information of the user.")
public class UserInformationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(name = "id", description = "It is the id of the user.", example = "14847")
    private Long id;

    @Schema(name = "firstName", description = "It is the first name of the user.", example = "Elif")
    private String firstName;

    @Schema(name = "lastName", description = "It is the last name of the user.", example = "GENÇ")
    private String lastName;

    @Schema(name = "email", description = "It is the email of the user", example = "test@gmail.com")
    private String email;

    @Schema(name = "city", description = "It is the the city where the user lives.", example = "Eskişehir")
    private String city;

    @Schema(name = "phone", description = "It is the phone of the user.", example = "5555555555")
    private String phone;

    @Schema(name = "lockedAccount", description = "It is the account lock info of that user.", example = "true")
    private boolean lockedAccount;

    @Schema(name = "deletedAccount", description = "This is the deletion information of that user's account.", example = "false")
    private boolean deletedAccount;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(name = "createDate", description = "It is the creation date of that user.", example = "01-08-2024 10:00:00")
    private LocalDateTime createDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(name = "updateDate", description = "It is the update date of that user.", example = "03-08-2024 10:00:00")
    private LocalDateTime updateDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(name = "lastLoginDate", description = "It is the last login date of that user.", example = "03-08-2024 10:00:00")
    private LocalDateTime lastLoginDate;

    public static UserInformationDTO fromUser(User user) {
        return UserInformationDTO
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .city(user.getCity())
                .phone(user.getPhone())
                .lockedAccount(user.isLocked())
                .deletedAccount(user.isDeleted())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate())
                .lastLoginDate(LocalDateTime.now())
                .build();

    }
}
