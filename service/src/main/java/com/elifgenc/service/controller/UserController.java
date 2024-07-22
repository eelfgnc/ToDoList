package com.elifgenc.service.controller;

import com.elifgenc.service.business.dto.CreateUserDTO;
import com.elifgenc.service.business.dto.SuccessResponseDTO;
import com.elifgenc.service.business.dto.UserDTO;
import org.springframework.http.ResponseEntity;


public interface UserController {

    ResponseEntity<UserDTO> getUserById(Long id);

    ResponseEntity<UserDTO> createUser(CreateUserDTO createUserDTO);

    ResponseEntity<UserDTO> updateUser(Long id, CreateUserDTO createUserDTO);

    ResponseEntity<SuccessResponseDTO> deleteUserById(Long id);

}