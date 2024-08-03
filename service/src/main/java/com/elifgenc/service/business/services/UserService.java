package com.elifgenc.service.business.services;

import com.elifgenc.service.business.dto.request.DeleteAccountDTO;
import com.elifgenc.service.business.dto.request.LockAccountDTO;
import com.elifgenc.service.business.dto.response.UserDTO;
import com.elifgenc.service.business.dto.response.UserInformationDTO;

import java.util.List;

public interface UserService {
    UserDTO getAllRolesByUserId(String email);

    List<UserInformationDTO> listOfAllUser();

    void lockedUserAccount(Long userId, LockAccountDTO lockAccountDTO);

    void deleteUserById(Long userId, DeleteAccountDTO deleteAccountDTO);
}
