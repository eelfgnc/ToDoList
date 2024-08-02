package com.elifgenc.service.business.dto.response;
import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "User DTO", description = "Contains user information.")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(name = "id", description = "It is the id of the user.", example = "14847")
    private Long id;
    @Schema(name = "username", description = "It is the email of the user.", example = "test@gmail.com")
    private String username;
    @Schema(name = "name", description = "It is the full name of the user.", example = "Elif GENÇ")
    private String name;
    @Schema(name = "roles", description = "It is the roles of the user.", example = "[User, Admin]")
    private List<String> roles;
}
