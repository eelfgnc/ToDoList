package com.elifgenc.service.business.services;

import com.elifgenc.service.business.dto.CreateUserDTO;
import com.elifgenc.service.business.dto.UserDTO;
import com.elifgenc.service.data.entity.User;

public interface UserService {

    UserDTO findById(Long id);

    UserDTO createUser(CreateUserDTO createUserDTO);

    UserDTO updateUser(Long id, CreateUserDTO createUserDTO);

    void deleteUser(Long id);

    User saveUser(User user);
}
