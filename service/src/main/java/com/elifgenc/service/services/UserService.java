package com.elifgenc.service.services;

import com.elifgenc.service.dto.CreateUserDTO;
import com.elifgenc.service.dto.UserDTO;

public interface UserService {

    public UserDTO findById(Long id);

    public UserDTO createUser(CreateUserDTO createUserDTO);

    public UserDTO updateUser(Long id, CreateUserDTO createUserDTO);

    public void deleteUser(Long id);
}
