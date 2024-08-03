package com.elifgenc.service.controller.impl;

import com.elifgenc.service.business.dto.request.DeleteAccountDTO;
import com.elifgenc.service.business.dto.request.LockAccountDTO;
import com.elifgenc.service.business.dto.response.SuccessResponseDTO;
import com.elifgenc.service.business.dto.response.UserDTO;
import com.elifgenc.service.business.dto.response.UserInformationDTO;
import com.elifgenc.service.config.JwtService;
import com.elifgenc.service.constant.ResponseConstant;
import com.elifgenc.service.controller.UserController;
import com.elifgenc.service.business.services.UserService;
import com.elifgenc.service.utils.frontend.ReactFrontend;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = ReactFrontend.REACT_FRONTEND_PORT_URL)
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "This tag contains services for adding, updating, deleting users and assigning user roles.")
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getRolesByUserId(@RequestHeader("Authorization") String authorization) {
        UserDTO userDTO = new UserDTO();
        if(authorization != null && authorization.startsWith("Bearer ") && jwtService.tokenValidate(authorization.substring(7))) {
            String email = jwtService.extractUsername(authorization.substring(7));
            userDTO = userService.getAllRolesByUserId(email);
        }
        return ResponseEntity.ok(userDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserInformationDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.listOfAllUser());
    }

    @Override
    @PutMapping("/{userId}/lock-account")
    public ResponseEntity<SuccessResponseDTO> lockedUserAccount(@PathVariable(name = "userId") Long userId, @Valid @RequestBody LockAccountDTO lockAccountDTO){
        userService.lockedUserAccount(userId, lockAccountDTO);
        return ResponseEntity.ok(new SuccessResponseDTO(ResponseConstant.SUCCESS_EDIT_MESSAGE.getMessage()));
    }

    @Override
    @PutMapping("/{userId}/delete-account")
    public ResponseEntity<SuccessResponseDTO> deleteUser(@PathVariable(name = "userId") Long userId, @Valid @RequestBody DeleteAccountDTO deleteAccountDTO){
        userService.deleteUserById(userId, deleteAccountDTO);
        return ResponseEntity.ok(new SuccessResponseDTO(ResponseConstant.SUCCESS_EDIT_MESSAGE.getMessage()));
    }
}