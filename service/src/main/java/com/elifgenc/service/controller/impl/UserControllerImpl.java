package com.elifgenc.service.controller.impl;

import com.elifgenc.service.business.dto.response.UserRoleDTO;
import com.elifgenc.service.config.JwtService;
import com.elifgenc.service.constant.ResponseConstant;
import com.elifgenc.service.controller.UserController;
import com.elifgenc.service.business.dto.CreateUserDTO;
import com.elifgenc.service.business.dto.SuccessResponseDTO;
import com.elifgenc.service.business.dto.UserDTO;
import com.elifgenc.service.business.services.UserService;
import com.elifgenc.service.utils.frontend.ReactFrontend;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ReactFrontend.REACT_FRONTEND_PORT_URL)
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDTO));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Long id, @Valid @RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(userService.updateUser(id, createUserDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> deleteUserById(@PathVariable(name = "id")Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(SuccessResponseDTO.builder().message(ResponseConstant.SUCCESS_DELETE_MESSAGE.getMessage()).build());
    }

    @Override
    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRoleDTO> getRolesByUserId(@RequestHeader("Authorization") String authorization) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        if(authorization != null && authorization.startsWith("Bearer ") && jwtService.tokenValidate(authorization.substring(7))) {
            String email = jwtService.extractUsername(authorization.substring(7));
            userRoleDTO = userService.getAllRolesByUserId(email);
        }
        return ResponseEntity.ok(userRoleDTO);
    }
}