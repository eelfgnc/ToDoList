package com.elifgenc.service.controller.impl;

import com.elifgenc.service.constant.ResponseConstant;
import com.elifgenc.service.controller.UserController;
import com.elifgenc.service.business.dto.CreateUserDTO;
import com.elifgenc.service.business.dto.SuccessResponseDTO;
import com.elifgenc.service.business.dto.UserDTO;
import com.elifgenc.service.business.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

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
}