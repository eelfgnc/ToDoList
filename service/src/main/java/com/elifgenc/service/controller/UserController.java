package com.elifgenc.service.controller;

import com.elifgenc.service.business.dto.request.DeleteAccountDTO;
import com.elifgenc.service.business.dto.request.LockAccountDTO;
import com.elifgenc.service.business.dto.response.SuccessResponseDTO;
import com.elifgenc.service.business.dto.response.UserDTO;
import com.elifgenc.service.business.dto.response.UserInformationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    @Operation(summary = "Provides information about the user",
            description = "Provides information about the user who logged in with authorization.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully provides user info.")
    })
    ResponseEntity<UserDTO> getRolesByUserId(String authorization);

    @Operation(summary = "Lists all user",
            description = "Lists all information of users.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully lists user.")
    })
    ResponseEntity<List<UserInformationDTO>> getAllUsers();

    @Operation(summary = "Updates the user's information",
            description = "Updates the user's account lock information.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updates user.")
    })
    ResponseEntity<SuccessResponseDTO> lockedUserAccount(Long userId, LockAccountDTO lockAccountDTO);

    @Operation(summary =  "Updates the user's information",
            description = "Updates the user's account information.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updates user.")
    })
    ResponseEntity<SuccessResponseDTO> deleteUser(Long userId, DeleteAccountDTO deleteAccountDTO);

}