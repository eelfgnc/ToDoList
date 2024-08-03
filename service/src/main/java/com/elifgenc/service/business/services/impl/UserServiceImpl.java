package com.elifgenc.service.business.services.impl;

import com.elifgenc.service.business.dto.request.DeleteAccountDTO;
import com.elifgenc.service.business.dto.request.LockAccountDTO;
import com.elifgenc.service.business.dto.response.UserDTO;
import com.elifgenc.service.business.dto.response.UserInformationDTO;
import com.elifgenc.service.constant.ErrorMessage;
import com.elifgenc.service.data.entity.User;
import com.elifgenc.service.exception.ObjectNotFoundException;
import com.elifgenc.service.data.repository.UserRepository;
import com.elifgenc.service.business.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Log4j2
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public UserDTO getAllRolesByUserId(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));
        List<String> roleNames = user.getUserRoles().stream().map(t -> t.getRole().getName()).collect(Collectors.toList());
        return  UserDTO
                .builder()
                .id(user.getId())
                .username(user.getEmail())
                .name(user.getFullName())
                .roles(roleNames)
                .build();
    }

    @Override
    public List<UserInformationDTO> listOfAllUser(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserInformationDTO::fromUser).collect(Collectors.toList());
    }

    @Override
    public void lockedUserAccount(Long userId, LockAccountDTO lockAccountDTO){
        System.out.println("isLocked: "+lockAccountDTO.getIsLocked());
        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));
        user.setLocked(lockAccountDTO.getIsLocked());
        userRepository.save(user);
    }

    @Override
    public  void deleteUserById(Long userId, DeleteAccountDTO deleteAccountDTO){
        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND));
        System.out.println("isDeleted: "+true);
        user.setDeleted(deleteAccountDTO.getIsDeleted());
        userRepository.save(user);
    }
}
