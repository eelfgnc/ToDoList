package com.elifgenc.service.controller;

import com.elifgenc.service.dto.CreateUserDTO;
import com.elifgenc.service.dto.SuccessResponseDTO;
import com.elifgenc.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;


public interface UserController {

    public ResponseEntity<UserDTO> getUserById(Long id);

    public ResponseEntity<UserDTO> createUser(CreateUserDTO createUserDTO);

    public ResponseEntity<UserDTO> updateUser(Long id, CreateUserDTO createUserDTO);

    public ResponseEntity<SuccessResponseDTO> deleteUserById(Long id);

}