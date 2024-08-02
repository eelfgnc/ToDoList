package com.elifgenc.service.business.services;

import com.elifgenc.service.business.dto.response.UserDTO;

public interface UserService {
    UserDTO getAllRolesByUserId(String email);
}
