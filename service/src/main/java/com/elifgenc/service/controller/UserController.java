package com.elifgenc.service.controller;

import com.elifgenc.service.business.dto.response.UserDTO;
import org.springframework.http.ResponseEntity;


public interface UserController {

    ResponseEntity<UserDTO> getRolesByUserId(String authorization);

}